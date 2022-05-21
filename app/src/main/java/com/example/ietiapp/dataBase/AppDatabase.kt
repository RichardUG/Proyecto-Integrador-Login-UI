package com.example.ietiapp.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.ietiapp.converters.Converters
import com.example.ietiapp.roomEntity.TasksEntity
import com.example.ietiapp.roomEntity.UserEntity
import com.example.ietiapp.roomDao.*

@Database(entities = [UserEntity::class, TasksEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun tasksDao(): TasksDao
}