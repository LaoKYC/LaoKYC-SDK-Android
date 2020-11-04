package com.codecamp.laokycmodule

import android.app.Application


class MyApp : Application() {

    var Token: String? = null

    override fun onCreate() {
        super.onCreate()


    }

    companion object {
        lateinit var Token: String
    }
}