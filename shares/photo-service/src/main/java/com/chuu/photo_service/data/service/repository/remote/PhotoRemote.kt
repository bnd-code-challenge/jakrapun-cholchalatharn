package com.chuu.photo_service.data.service.repository.remote

import com.chuu.network.base.ApiManager
import com.chuu.photo_service.data.model.PhotoItem
import com.chuu.photo_service.data.service.api.PhotoService
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory

interface IPhotoRemote {
    suspend fun getPhotos() : Response<List<PhotoItem>>
}
class PhotoRemote(
    private val apiManager: ApiManager
): IPhotoRemote {

    private fun getPhotoService(): PhotoService {
        return apiManager.init(
            baseUrl = "https://jsonplaceholder.typicode.com",
            converter = GsonConverterFactory.create()
        ).create(PhotoService::class.java)
    }

    override suspend fun getPhotos(): Response<List<PhotoItem>> {
        return getPhotoService().getPhotos()
    }
}