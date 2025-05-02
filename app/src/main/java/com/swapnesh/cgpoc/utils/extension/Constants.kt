package com.swapnesh.cgpoc.utils.extension

import android.os.Looper

const val BASE_URL = "https://dummyjson.com/"


fun isOnMainThread() = Looper.myLooper() == Looper.getMainLooper()