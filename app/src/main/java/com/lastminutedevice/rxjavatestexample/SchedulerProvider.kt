package com.lastminutedevice.rxjavatestexample

import io.reactivex.Scheduler

interface SchedulerProvider {

    fun background(): Scheduler

    fun ui(): Scheduler
}
