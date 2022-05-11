package com.jomibusa.pruebaceiba.data.roomDatabase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jomibusa.pruebaceiba.data.roomDatabase.entities.PostEntity
import com.jomibusa.pruebaceiba.data.roomDatabase.entities.UserEntity

@Dao
interface UserDAO {

    @Query("SELECT * FROM user_table")
    fun getAllUser(): List<UserEntity>?

    @Query("SELECT * FROM post_table WHERE userId = :userId")
    fun getAllPost(userId: Int): List<PostEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllUser(vararg users: UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPost(vararg posts: PostEntity)

}