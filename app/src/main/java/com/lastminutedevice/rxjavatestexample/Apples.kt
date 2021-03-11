package com.lastminutedevice.rxjavatestexample

import com.uber.autodispose.AutoDispose
import io.reactivex.Single
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
        Single.just(true)
            .subscribeOn(Schedulers.trampoline())
            .observeOn(Schedulers.trampoline())
            .map { throw Throwable("This should trigger onError in Apples.") }
            .`as`(AutoDispose.autoDisposable(lifecycleScopeProvider))
            .subscribe (
                { tree.happyState() }, // onSuccess
                { tree.errorState() }  // onError
            )
    }
}
