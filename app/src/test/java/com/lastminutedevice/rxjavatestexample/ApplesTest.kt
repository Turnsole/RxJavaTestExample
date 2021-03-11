package com.lastminutedevice.rxjavatestexample

import io.reactivex.Scheduler
import io.reactivex.Single
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

    private val errorString = "Hard-Coded Exception"

    private val testSchedulers : SchedulerProvider = object : SchedulerProvider {
        override fun background(): Scheduler {
            return Schedulers.trampoline()
        }

        override fun ui(): Scheduler {
            return Schedulers.trampoline()
        }
    }

    @Mock
    private lateinit var mockTree : Tree

    @Mock
    lateinit var mockDataSource : SimpleDataSource

    @Before
    fun setup() {
        apples = Apples(mockTree, testSchedulers, mockDataSource)
        apples?.beginLifecycle()
    }

    @After
    fun cleanup() {
        apples?.endLifecycle()
    }

    @Test
    fun test() {
        Mockito.`when`(mockDataSource.stream()).thenReturn(Single.error<Boolean>(Throwable(errorString)))
        apples?.doSomething()
        Mockito.verify(mockTree).errorState(errorString)
    }
}
