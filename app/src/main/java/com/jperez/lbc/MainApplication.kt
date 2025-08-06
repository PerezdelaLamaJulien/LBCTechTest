package com.jperez.lbc

import android.app.Application
import com.jperez.lbc.data.di.dataKoinModule
import com.jperez.lbc.feature.di.featureKoinModule
import com.jperez.lbc.domain.di.domainKoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(featureKoinModule, domainKoinModule, dataKoinModule)
        }
    }
}