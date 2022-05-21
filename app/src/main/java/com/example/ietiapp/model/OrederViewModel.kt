package com.example.ietiapp.model

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.example.ietiapp.dataBase.AppDatabase
import com.example.ietiapp.roomDao.TasksDao
import com.example.ietiapp.roomDao.UserDao

class OrederViewModel : ViewModel(){
    //var tasks_connect: TaskInterface = RetrofitGenerator().getInstanceTasks()!!.create(TaskInterface::class.java)

    private var db: AppDatabase? =null

    lateinit var userDao: UserDao
    lateinit var tasksDao: TasksDao

    fun buildDatabase(context: Context){
        db = Room.databaseBuilder(
            context,
            AppDatabase::class.java, "database-name"
        ).allowMainThreadQueries().build()

        userDao = db!!.userDao()
        tasksDao = db!!.tasksDao()
    }

}