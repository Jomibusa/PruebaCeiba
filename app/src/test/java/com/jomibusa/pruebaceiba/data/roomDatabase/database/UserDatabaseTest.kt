package com.jomibusa.pruebaceiba.data.roomDatabase.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.jomibusa.pruebaceiba.data.roomDatabase.entities.PostEntity
import com.jomibusa.pruebaceiba.data.roomDatabase.entities.UserEntity
import junit.framework.TestCase
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.io.IOException


@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
class UserDatabaseTest : TestCase(){

    private lateinit var db: UserDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, UserDatabase::class.java
        ).allowMainThreadQueries().build()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadFirstInListTheSameUser() {
        val user = UserEntity(19, "José Bueno", "jomibusa1903@gmail.com", "200-123-456-2342")
        db.getUserDao().insertUser(user)
        val listUser = db.getUserDao().getAllUser()
        if (listUser != null && listUser.isNotEmpty()) {
            val newUser = listUser[0]
            assertThat(newUser.name, equalTo(user.name))
        }
    }

    @Test
    @Throws(Exception::class)
    fun writePostAndReadFirstInListTheSameTitle() {
        val post = PostEntity(9, 19,"Mi Primer Post", "Prueba Unitaria para entrar a ceiba")
        db.getUserDao().insertPost(post)
        val listPosts = db.getUserDao().getAllPost(19)
        if (listPosts != null && listPosts.isNotEmpty()) {
            val newUser = listPosts[0]
            assertThat(newUser.title, equalTo(post.title))
        }
    }

}