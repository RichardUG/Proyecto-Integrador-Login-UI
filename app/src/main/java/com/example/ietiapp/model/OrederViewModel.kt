package com.example.ietiapp.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ietiapp.generators.RetrofitGenerator
import com.example.ietiapp.interfaces.AuthInterface
import com.example.ietiapp.interfaces.TaskInterface
import retrofit2.Retrofit
import retrofit2.create

class OrederViewModel : ViewModel(){
    //private val _retrofitTasks = MutableLiveData<TaskInterface>()
    //val Tasks : LiveData<TaskInterface> = _retrofitTasks

    //private val _retrofitUsers = MutableLiveData<AuthInterface>()
    //val Auth : LiveData<AuthInterface> = _retrofitUsers

//    val storage:ImplementedStorage = TODO()

    //val retrofitTasks: Retrofit = RetrofitGenerator().getInstanceTasks()
  // private val retrofitUsers: Retrofit? = RetrofitGenerator().getInstanceUsers(storage)
    var tasks_connect: TaskInterface = RetrofitGenerator().getInstanceTasks()!!.create(TaskInterface::class.java)

    init {
        // _retrofitTasks.value = _retrofitTasks.value?.javaClass?.let { retrofitTasks?.create(it) }
       // _retrofitUsers.value = retrofitUsers?.create(_retrofitUsers.value?.javaClass)

    }
}