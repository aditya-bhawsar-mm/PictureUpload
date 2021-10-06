package com.example.pictureupload

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.pictureupload.usecases.AuthUseCase
import com.example.pictureupload.usecases.PicDbUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject


@HiltWorker
class UploadWorker @AssistedInject constructor(
    @Assisted val ctx: Context,
    @Assisted val params: WorkerParameters,
    private val authUseCase: AuthUseCase,
    private val picDbUseCase: PicDbUseCase
) : CoroutineWorker(ctx, params) {

    override suspend fun doWork(): Result {

        return Result.success()
    }
}