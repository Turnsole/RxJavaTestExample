package com.lastminutedevice.rxjavatestexample

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class ApplesTest {

    private var apples : Apples? = null

    @Mock
    private lateinit var mockTree : Tree

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this) // TODO also try the mockito runner

        apples = Apples(mockTree)
        apples?.beginLifecycle()
    }

    @After
    fun cleanup() {
        apples?.endLifecycle()
    }

    @Test
    fun test() {
        apples?.doSomething()

        Mockito.verify(mockTree.errorState())
    }
}
