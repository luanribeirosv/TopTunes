package com.toptunes.presentation.di

import com.toptunes.presentation.album_list.AlbumListViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {
    viewModelOf(::AlbumListViewModel)
}