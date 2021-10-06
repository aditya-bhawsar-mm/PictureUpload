package com.example.pictureupload

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.pictureupload.domain.StorageResult
import com.example.pictureupload.usecases.AuthUseCase
import com.example.pictureupload.usecases.PicDbUseCase
import com.example.pictureupload.usecases.StorageUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.io.ByteArrayOutputStream
import java.lang.Exception
import java.util.concurrent.CountDownLatch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.takeWhile
import kotlinx.coroutines.withContext

@HiltWorker
class UploadWorker @AssistedInject constructor(
    @Assisted val ctx: Context,
    @Assisted val params: WorkerParameters,
    private val authUseCase: AuthUseCase,
    private val picDbUseCase: PicDbUseCase,
    private val storageUseCase: StorageUseCase
) : CoroutineWorker(ctx, params) {

    override suspend fun doWork(): Result {

        var result = Result.retry()

        try {
            val userUid = authUseCase.getUserUID()

            if(userUid != null){
                storageUseCase.createStorageRef(userUid)

                val pics = picDbUseCase.getToUploadPics()

                val countDownLatch = CountDownLatch(pics.size)
                result = Result.success()

                for(pic in pics){
                    val inputSteam = ctx.assets.open(pic.path)
                    val bitmap = BitmapFactory.decodeStream(inputSteam)
                    val baos = ByteArrayOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                    val data = baos.toByteArray()
                    
                    storageUseCase.uploadFile(data).takeWhile{ it != StorageResult.Complete }.collect {storageResult ->
                        when(storageResult){
                            is StorageResult.Loading->{  }
                            is StorageResult.Failure->{
                                countDownLatch.countDown()
                                result = Result.retry()
                            }
                            is StorageResult.Success->{
                                pic.uploaded= true
                                picDbUseCase.updatePicDetails(pic)
                                countDownLatch.countDown()
                            }
                            is StorageResult.Complete ->{}
                        }
                    }
                }
                println("calling await ${pics.size}")
                countDownLatch.await()
                println("Opened")
            }else{
                picDbUseCase.deleteAllPics()
            }

        }
        catch (e: Exception){
            e.printStackTrace()
            result = Result.retry()
        }

        println("Result $result")

        return result
    }
}