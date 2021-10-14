package com.mutualmobile.photosclone

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.mutualmobile.photosclone.domain.PicDetails
import com.mutualmobile.photosclone.usecases.PicDbUseCase
import com.mutualmobile.photosclone.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PictureViewModel @Inject constructor(private val picDbUseCase: PicDbUseCase) : ViewModel() {

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
