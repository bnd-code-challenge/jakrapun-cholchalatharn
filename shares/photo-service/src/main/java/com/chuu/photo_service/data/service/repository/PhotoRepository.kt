package com.chuu.photo_service.data.service.repository

import com.chuu.network.repository.NetworkBoundResource
import com.chuu.photo_service.data.model.PhotoItem
import com.chuu.photo_service.data.service.repository.remote.IPhotoRemote
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface IPhotoRepository {
    fun getPhotosFlow(): Flow<List<PhotoItem>>
}

class PhotoRepository(
    private val photoRemote: IPhotoRemote
): IPhotoRepository {

    override fun getPhotosFlow(): Flow<List<PhotoItem>> {
        return object : NetworkBoundResource<List<PhotoItem>>() {
            override suspend fun createCall(): Response<List<PhotoItem>> {
                return photoRemote.getPhotos()
            }
        }.asFlow()
    }
}