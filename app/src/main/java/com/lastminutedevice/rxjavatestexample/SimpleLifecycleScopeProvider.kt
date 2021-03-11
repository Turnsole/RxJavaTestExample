package com.lastminutedevice.rxjavatestexample

import com.jakewharton.rxrelay2.BehaviorRelay
import com.uber.autodispose.lifecycle.CorrespondingEventsFunction
import com.uber.autodispose.lifecycle.LifecycleEndedException
import com.uber.autodispose.lifecycle.LifecycleScopeProvider
import io.reactivex.Observable

/**
 * The simplest possible lifecycle scope provider I could come up with.
 */
class SimpleLifecycleScopeProvider : LifecycleScopeProvider<Boolean> {

    private val behaviorRelay: BehaviorRelay<Boolean> = BehaviorRelay.create()

    override fun lifecycle(): Observable<Boolean> {
        return behaviorRelay.hide()
    }

    override fun correspondingEvents(): CorrespondingEventsFunction<Boolean> {
        return CorrespondingEventsFunction { event ->
            if (!event) {
                throw LifecycleEndedException()
            } else {
                event
            }
        }
    }

    override fun peekLifecycle(): Boolean? {
        return behaviorRelay.value
    }

    fun start() {
        behaviorRelay.accept(true)
    }

    fun stop() {
        behaviorRelay.accept(false)
    }
}
