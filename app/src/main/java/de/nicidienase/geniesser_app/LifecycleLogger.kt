package de.nicidienase.geniesser_app

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import timber.log.Timber

class LifecycleLogger(val tag: String) : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        Timber.tag(tag).i("onCreate")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onstart() {
        Timber.tag(tag).i("onStart")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onresume() {
        Timber.tag(tag).d("onResume")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onpause() {
        Timber.tag(tag).d("onPause")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onstop() {
        Timber.tag(tag).i("onStop")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun ondestroy() {
        Timber.tag(tag).d("onDestroy")
    }
}
