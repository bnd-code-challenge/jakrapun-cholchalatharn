package com.chuu.photogallery.photo_list_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chuu.photo_service.data.model.PhotoItem
import com.chuu.photo_service.data.service.repository.IPhotoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PhotoListScreenViewModel(
    private val photoRepository: IPhotoRepository
): ViewModel() {
    private val _isLoading = MutableStateFlow(false)
    private val _error = MutableStateFlow<String?>(null)
    private val _photoList = MutableStateFlow<List<PhotoItem>>(emptyList())

    val uiState = combine(
        _isLoading,
        _error,
        _photoList
    ) { loading, error, photos ->

        UIState(
            isLoading = loading,
            photos = photos,
            error = error
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = UIState()
    )

    init {
        fetchPhotos()
    }

    private fun fetchPhotos() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                photoRepository.getPhotosFlow().collect {
                    _error.value = null
                    _isLoading.value = false
                    _photoList.value = it
                }
            } catch (e: Exception) {
                _error.value = e.message
                _isLoading.value = false
            }
        }
    }

    data class UIState(
        val isLoading: Boolean = false,
        val photos: List<PhotoItem> = emptyList(),
        val error: String? = null
    )
}