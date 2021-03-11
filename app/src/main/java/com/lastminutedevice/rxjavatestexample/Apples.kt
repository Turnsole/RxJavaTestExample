package com.lastminutedevice.rxjavatestexample

import com.uber.autodispose.AutoDispose
import io.reactivex.Single

class Apples(private val tree: Tree, private val schedulers: SchedulerProvider) {

    private val lifecycleScopeProvider = SimpleLifecycleScopeProvider()

    fun beginLifecycle() {
        lifecycleScopeProvider.start()
    }

    fun endLifecycle() {
        lifecycleScopeProvider.stop()
    }

    fun doSomething() {
        Single.just(true)
            .subscribeOn(schedulers.background())
            .observeOn(schedulers.ui())
            .map { throw Throwable("This should trigger onError in Apples.") }
            .`as`(AutoDispose.autoDisposable(lifecycleScopeProvider))
            .subscribe (
                { tree.happyState() }, // onSuccess
                { throwable -> tree.errorState(throwable.message) }  // onError
            )
    }
}
