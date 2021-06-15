package de.nicidienase.geniesser_app

import android.app.Application
import com.facebook.stetho.Stetho
import timber.log.Timber

class GourmetApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
            Timber.plant(Timber.DebugTree())
        }
    }
}
