package com.lastminutedevice.rxjavatestexample

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ApplesTest {

    private var apples : Apples? = null

    private val testSchedulers = object : SchedulerProvider {
        override fun background(): Scheduler {
            return Schedulers.trampoline()
        }

        override fun ui(): Scheduler {
            return Schedulers.trampoline()
        }
    }

    @Mock
    private lateinit var mockTree : Tree

    @Before
    fun setup() {
        apples = Apples(mockTree, testSchedulers)
        apples?.beginLifecycle()
    }

    @After
    fun cleanup() {
        apples?.endLifecycle()
    }

    @Test
    fun test() {
        apples?.doSomething()
        Mockito.verify(mockTree).errorState("This should trigger onError in Apples.")
    }
}
