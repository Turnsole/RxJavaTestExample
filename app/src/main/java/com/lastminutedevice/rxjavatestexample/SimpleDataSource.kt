package com.lastminutedevice.rxjavatestexample

import io.reactivex.Single

interface SimpleDataSource {

    fun stream() : Single<Boolean>
}
