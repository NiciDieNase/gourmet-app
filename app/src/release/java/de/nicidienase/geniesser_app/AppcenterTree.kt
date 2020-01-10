package de.nicidienase.geniesser_app

import android.util.Log
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import timber.log.Timber

class AppcenterTree : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority in listOf(Log.VERBOSE, Log.DEBUG)) {
            return
        }

        Analytics.trackEvent("$tag: $message")

        t?.let { Crashes.trackError(it) }
    }
}