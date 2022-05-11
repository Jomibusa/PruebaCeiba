package com.jomibusa.pruebaceiba.data.roomDatabase.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jomibusa.pruebaceiba.data.roomDatabase.dao.UserDAO
import com.jomibusa.pruebaceiba.data.roomDatabase.entities.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class UserDatabase : RoomDatabase() {

    abstract fun getUserDao(): UserDAO

    companion object {

        @Volatile
        private var instance: UserDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            UserDatabase::class.java, "users.db"
        ).allowMainThreadQueries().build()

    }

}