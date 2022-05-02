package com.hfad.android.roomdbtest

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserModel::class], version = 1)
abstract class UserDB: RoomDatabase () {
    abstract fun userDao(): UserDao


    companion object {
        private var INSTANCE: UserDB? = null

        fun getInstance(context: Context): UserDB{

            return INSTANCE ?: Room.databaseBuilder(context.applicationContext,UserDB::class.java, "User_database")
                .build().also {
                    INSTANCE = it
                }
        }
    }

}