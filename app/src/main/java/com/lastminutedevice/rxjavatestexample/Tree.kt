package com.lastminutedevice.rxjavatestexample

import java.lang.RuntimeException

class Tree {

    fun happyState() {
        /* No-op. */
    }

    fun errorState(message: String?) {
        throw RuntimeException(message)
    }
}
