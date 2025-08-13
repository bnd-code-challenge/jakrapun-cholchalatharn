package com.chuu.photo_service.data.service.api

import com.chuu.photo_service.data.model.PhotoItem
import retrofit2.Response
import retrofit2.http.GET

interface PhotoService {
    @GET("/photos")
    suspend fun getPhotos(): Response<List<PhotoItem>>
}