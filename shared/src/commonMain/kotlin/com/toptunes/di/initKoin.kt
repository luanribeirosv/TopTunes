package com.toptunes.di

import com.toptunes.data.di.dataModule
import com.toptunes.presentation.di.presentationModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(
            dataModule,
            presentationModule
        )
    }
}