package de.nicidienase.geniesser_app

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import timber.log.Timber

class LifecycleLogger : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        Timber.i("onCreate")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onstart() {
        Timber.i("onStart")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onresume() {
        Timber.i("onResume")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onpause() {
        Timber.i("onPause")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onstop() {
        Timber.i("onStop")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun ondestroy() {
        Timber.i("onDestroy")
    }
}