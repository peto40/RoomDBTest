package com.hfad.android.roomdbtest

import androidx.room.*

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getAllUsers(user: List<UserModel>)

    @Query("SELECT * FROM user LIMIT 1")
    fun getFirstElement() : UserModel

    @Query("SELECT * FROM user WHERE surname = :paramSurname")
    fun getUserBySurname(paramSurname: String): List<UserModel>

    @Query("SELECT * FROM user WHERE surname LIKE :paramSurname")
    fun getUserBySurnameLike(paramSurname: String): List<UserModel>

    @Query("SELECT * FROM user ")

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(userModel: UserModel)

    @Insert
    fun addUser(user: List<UserModel>)

    @Delete
    fun deleteUser(user: UserModel)

    @Update
    fun update(user: UserModel)


}