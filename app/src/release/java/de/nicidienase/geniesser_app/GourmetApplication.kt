package de.nicidienase.geniesser_app

import android.app.Application
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import com.microsoft.appcenter.distribute.Distribute

class GourmetApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppCenter.start(
            this,
            BuildConfig.APPCENTER_ID,
            Analytics::class.java,
            Crashes::class.java,
            Distribute::class.java
        )
    }
}