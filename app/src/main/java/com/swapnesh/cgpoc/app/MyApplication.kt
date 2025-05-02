package com.swapnesh.cgpoc.app

import android.app.Application
import com.swapnesh.cgpoc.di.myModule

import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext

import org.koin.core.context.startKoin

@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
             androidContext(this@MyApplication)
             modules(myModule)
        }

    }
}