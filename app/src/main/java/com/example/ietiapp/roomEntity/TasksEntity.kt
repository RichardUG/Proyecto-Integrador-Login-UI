package com.example.ietiapp.roomEntity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
class TasksEntity (
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "status") val status: String?,
    @ColumnInfo(name = "assignedTo") val assignedTo: String?,
    @ColumnInfo(name = "dueDate") val dueDate: String?,
    @ColumnInfo(name = "created") val created: LocalDate?
)