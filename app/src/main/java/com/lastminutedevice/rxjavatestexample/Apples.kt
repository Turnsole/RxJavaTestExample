package com.lastminutedevice.rxjavatestexample

import com.uber.autodispose.AutoDispose
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class Apples(private val tree: Tree) {

    private val lifecycleScopeProvider = SimpleLifecycleScopeProvider()

    fun beginLifecycle() {
        lifecycleScopeProvider.start()
    }

    fun endLifecycle() {
        lifecycleScopeProvider.stop()
    }

    fun doSomething() {
        Observable.just(true)
            .subscribeOn(Schedulers.computation())
            .map { throw Throwable("This should trigger onError in Apples.") }
            .`as`(AutoDispose.autoDisposable(lifecycleScopeProvider))
            .subscribe (
                { tree.happyState() }, // onSuccess
                { tree.errorState() }  // onError
            )
    }
}
