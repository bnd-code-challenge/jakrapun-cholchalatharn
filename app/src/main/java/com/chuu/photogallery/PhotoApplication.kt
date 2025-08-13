package com.chuu.photogallery

import android.app.Application
import com.chuu.network.di.networkModule
import com.chuu.photo_service.di.photoServiceModule
import com.chuu.photogallery.di.photoModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class PhotoApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            // Log Koin into Android logger
            androidLogger()
            // Reference Android context
            androidContext(this@PhotoApplication)
            // Load modules
            modules(appModule)
        }
    }
}

val appModule = listOf(
    networkModule,
    photoServiceModule,
    photoModule
)