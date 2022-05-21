package com.example.ietiapp.roomDao

import androidx.room.*
import com.example.ietiapp.roomEntity.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM userEntity")
    fun getAll(): List<UserEntity>

    @Query("SELECT * FROM userEntity WHERE id IN (:userId)")
    fun findById(userId: String): UserEntity

    @Query("SELECT * FROM userEntity WHERE name IN (:queryParam) or lastName IN (:queryParam)")
    fun findUsersWithNameOrLastNameLike(queryParam: String): List<UserEntity>

    @Insert
    fun create(user: UserEntity)

    @Update
    fun update(user: UserEntity)

    @Delete
    fun delete(user: UserEntity)
}