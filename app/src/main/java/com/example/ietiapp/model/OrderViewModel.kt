package com.example.ietiapp.model

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.example.ietiapp.dataBase.AppDatabase
import com.example.ietiapp.generators.RetrofitGenerator
import com.example.ietiapp.interfaces.TaskInterface
import com.example.ietiapp.roomDao.TasksDao
import com.example.ietiapp.roomDao.UserDao
import com.example.ietiapp.storage.ImplementedStorage
import com.example.ietiapp.storage.Storage

class OrderViewModel : ViewModel(){
    var tasks_connect: TaskInterface = RetrofitGenerator().getInstanceTasks()!!.create(TaskInterface::class.java)

    private var _storage = MutableLiveData<Storage>()
    var storage: LiveData<Storage> = _storage

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

    fun startStorage(implementedStorage: ImplementedStorage){
        _storage.value = implementedStorage
    }

}