package com.example.pictureupload

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.pictureupload.domain.PicDetails
import com.example.pictureupload.usecases.PicDbUseCase
import com.example.pictureupload.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class PictureViewModel @Inject constructor(private val picDbUseCase: PicDbUseCase) :ViewModel() {

    fun insertPicDetails(picDetails: PicDetails, ctx: Context) = viewModelScope.launch {

        picDbUseCase.insertPicDetails(picDetails)

        val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val oneTimeReq = OneTimeWorkRequestBuilder<UploadWorker>().setConstraints(constraints).build()

        val workManager = WorkManager.getInstance(ctx)

        workManager.beginUniqueWork(
            Constants.UPLOAD_WORKER_TAG,
            ExistingWorkPolicy.REPLACE,
            oneTimeReq
        ).enqueue()
    }

    fun getPathPresence(path: String) = picDbUseCase.getPicPresence(path = path)

}