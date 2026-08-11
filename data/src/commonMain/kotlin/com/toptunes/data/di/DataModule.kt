package com.toptunes.data.di

import com.toptunes.data.log.KermitLogger
import com.toptunes.data.networking.HttpClientFactory
import com.toptunes.data.service.KtorAlbumService
import com.toptunes.domain.log.TopTunesLogger
import com.toptunes.domain.service.AlbumService
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformDataModule: Module

val dataModule = module {
    includes(platformDataModule)
    single<TopTunesLogger> { KermitLogger }
    single {
        HttpClientFactory(get()).create(get())
    }
    singleOf(::KtorAlbumService) bind AlbumService::class
}