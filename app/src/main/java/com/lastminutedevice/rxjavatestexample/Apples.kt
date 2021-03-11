package com.lastminutedevice.rxjavatestexample

import com.uber.autodispose.AutoDispose

class Apples(
    private val tree: Tree,
    private val schedulers: SchedulerProvider,
    private val dataSource: SimpleDataSource) {

    private val lifecycleScopeProvider = SimpleLifecycleScopeProvider()

    fun beginLifecycle() {
        lifecycleScopeProvider.start()
    }

    fun endLifecycle() {
        lifecycleScopeProvider.stop()
    }

    fun doSomething() {
        dataSource.stream()
            .subscribeOn(schedulers.background())
            .observeOn(schedulers.ui())
            .`as`(AutoDispose.autoDisposable(lifecycleScopeProvider))
            .subscribe (
                { tree.happyState() }, // onSuccess
                { throwable -> tree.errorState(throwable.message) }  // onError
            )
    }
}
