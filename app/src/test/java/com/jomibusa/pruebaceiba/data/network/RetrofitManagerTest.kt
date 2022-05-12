package com.jomibusa.pruebaceiba.data.network

import com.jomibusa.pruebaceiba.data.model.Post
import com.jomibusa.pruebaceiba.data.model.User
import io.mockk.MockKAnnotations
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test


class RetrofitManagerTest {

    private lateinit var retrofitManager: RetrofitManager

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        retrofitManager = RetrofitManager()
    }

    @Test
    fun getTotalUsersFromApiAndReturnTheListOrNull() {

        val listUser: List<User> = mutableListOf()

        retrofitManager.getListUsers {

            if (it != null && it.isNotEmpty()) {
                assertThat(it, equalTo(listUser))
            } else {
                assertThat(it, equalTo(null))
            }
        }

    }

    @Test
    fun getPostsByUserFromApiAndReturnTheListOrNull() {

        val idUser = (1..10).random().toString()

        val listPosts: List<Post> = mutableListOf()

        retrofitManager.getListPosts(idUser) {
            if (it != null && it.isNotEmpty()) {
                assertThat(it, equalTo(listPosts))
            } else {
                assertThat(it, equalTo(null))
            }
        }

    }

}