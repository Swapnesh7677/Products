package com.swapnesh.cgpoc.di

import com.swapnesh.cgpoc.utils.helper.PreferenceHelper
import org.koin.dsl.module

val myModule = module {
    // declaration of singleton instances
    single { PreferenceHelper(get()) }
}
