package com.chuu.network.di

import com.chuu.network.base.ApiManager
import com.chuu.network.base.NetworkClient
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val networkModule = module {
    single<CoroutineDispatcher>(named("io")) { Dispatchers.IO }
    single<CoroutineDispatcher>(named("main")) { Dispatchers.Main }
    single<CoroutineDispatcher>(named("default")) { Dispatchers.Default }

    factory {
        ApiManager(
            get(),
            Retrofit.Builder()
        )
    }

    factory {
        NetworkClient(
            androidContext()
        )
    }
}