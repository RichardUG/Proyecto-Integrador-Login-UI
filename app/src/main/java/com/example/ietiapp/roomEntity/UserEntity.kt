package com.example.ietiapp.roomEntity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ietiapp.data.RoleEnum
import java.time.LocalDate

@Entity
class UserEntity(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "email") val email: String?,
    @ColumnInfo(name = "lastName") val lastName: String?,
    @ColumnInfo(name = "createdAt") val createdAt: LocalDate?,
    @ColumnInfo(name = "passwordHash") val passwordHash: String?,
    @ColumnInfo(name = "roles") val roles: RoleEnum?
)