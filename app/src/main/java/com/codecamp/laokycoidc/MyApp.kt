package com.codecamp.laokycoidc

import android.app.Application
import com.codecamp.laokycoidc.setup.appDependencies
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {

            androidContext(this@MyApp)
            modules(appDependencies)
        }
    }
}