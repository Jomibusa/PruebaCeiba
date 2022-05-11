package com.jomibusa.pruebaceiba.data.network

import io.mockk.MockKAnnotations
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test


class RetrofitManagerTest {

    lateinit var retrofitManager: RetrofitManager

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        retrofitManager = RetrofitManager()
    }

    @Test
    fun getUsersFromApiAndReturnTheListOrNull() {

        retrofitManager.getListUsers {
            assertTrue(it != null && it.isNotEmpty())
        }

    }

}