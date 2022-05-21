package com.example.ietiapp.roomDao

import androidx.room.*
import com.example.ietiapp.roomEntity.TasksEntity

@Dao
interface TasksDao {
    @Query("SELECT * FROM tasksEntity")
    fun getAll(): List<TasksEntity>

    @Query("SELECT * FROM tasksEntity WHERE id IN (:taskId)")
    fun findById(taskId: String): TasksEntity

    @Insert
    fun create(task: TasksEntity)

    @Update
    fun update(task: TasksEntity)

    @Delete
    fun delete(task: TasksEntity)
}