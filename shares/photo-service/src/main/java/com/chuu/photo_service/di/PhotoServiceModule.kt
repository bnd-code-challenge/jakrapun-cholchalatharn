package com.chuu.photo_service.di

import com.chuu.photo_service.data.service.repository.IPhotoRepository
import com.chuu.photo_service.data.service.repository.PhotoRepository
import com.chuu.photo_service.data.service.repository.remote.IPhotoRemote
import com.chuu.photo_service.data.service.repository.remote.PhotoRemote
import org.koin.dsl.module

val photoServiceModule = module {

    single<IPhotoRemote> {
        PhotoRemote(
            get()
        )
    }

    single<IPhotoRepository> {
        PhotoRepository(
            get()
        )
    }
}