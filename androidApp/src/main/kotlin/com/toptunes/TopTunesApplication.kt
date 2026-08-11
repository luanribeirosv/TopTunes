package com.toptunes

import android.app.Application
import com.toptunes.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class TopTunesApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(this@TopTunesApplication)
            androidLogger()
        }
    }
}