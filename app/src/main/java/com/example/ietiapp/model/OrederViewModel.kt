package com.example.ietiapp.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ietiapp.generators.RetrofitGenerator
import com.example.ietiapp.interfaces.AuthInterface
import com.example.ietiapp.interfaces.TaskInterface
import com.example.ietiapp.storage.ImplementedStorage
import com.example.ietiapp.storage.Storage
import retrofit2.Retrofit
import retrofit2.create

class OrederViewModel : ViewModel(){
    private val _retrofitTasks = MutableLiveData<TaskInterface>()
    val Tasks : LiveData<TaskInterface> = _retrofitTasks

    private val _retrofitUsers = MutableLiveData<AuthInterface>()
    val Auth : LiveData<AuthInterface> = _retrofitUsers

//    val storage:ImplementedStorage = TODO()

    private val retrofitTasks: Retrofit? = RetrofitGenerator().getInstanceTasks()
  // private val retrofitUsers: Retrofit? = RetrofitGenerator().getInstanceUsers(storage)

    init {
        _retrofitTasks.value = retrofitTasks?.create()
       // _retrofitUsers.value = retrofitUsers?.create(_retrofitUsers.value?.javaClass)
    }
}