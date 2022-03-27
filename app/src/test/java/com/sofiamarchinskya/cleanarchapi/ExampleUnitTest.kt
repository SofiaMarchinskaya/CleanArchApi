package com.sofiamarchinskya.cleanarchapi

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.runTest
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testFlow() = runTest{
        val numFlow = flowOf(1,2,3,4,5,6,7)
        numFlow.filter { it%2==0 }.map { it*4 }.collect {
          println(it)
        }
    }
}