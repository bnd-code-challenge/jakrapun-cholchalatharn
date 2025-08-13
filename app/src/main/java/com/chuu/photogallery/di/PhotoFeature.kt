package com.chuu.photogallery.di

import com.chuu.photogallery.photo_list_screen.PhotoListScreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val photoModule = module {
    viewModel {
        PhotoListScreenViewModel(
            get()
        )
    }
}