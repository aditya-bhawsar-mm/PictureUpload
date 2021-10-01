package com.example.pictureupload

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pictureupload.domain.PicDetails
import com.example.pictureupload.usecases.PicDbUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class PictureViewModel @Inject constructor(private val picDbUseCase: PicDbUseCase) :ViewModel() {

    fun insertPicDetails(picDetails: PicDetails) = viewModelScope.launch {
        picDbUseCase.insertPicDetails(picDetails)
    }

    fun getPathPresence(path: String) = picDbUseCase.getPicPresence(path = path)

}