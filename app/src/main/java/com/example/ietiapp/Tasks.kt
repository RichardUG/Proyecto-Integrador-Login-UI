package com.example.ietiapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.ietiapp.data.LoginDto
import com.example.ietiapp.data.RoleEnum
import com.example.ietiapp.data.TasksDto
import com.example.ietiapp.data.TokenDto
import com.example.ietiapp.databinding.FragmentTasksBinding
import com.example.ietiapp.generators.RetrofitGenerator
import com.example.ietiapp.interfaces.AuthInterface
import com.example.ietiapp.interfaces.TaskInterface
import com.example.ietiapp.model.OrderViewModel
import com.example.ietiapp.roomEntity.UserEntity
import com.example.ietiapp.storage.ImplementedStorage
import com.example.ietiapp.storage.Storage
import com.example.socialneighborhood.adapter.TasksAdapter
import rx.functions.Action1
import rx.schedulers.Schedulers
import java.time.LocalDate


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */

class Tasks : Fragment() {

    private var binding: FragmentTasksBinding? = null
    private val sharedViewModel: OrderViewModel by activityViewModels()

    val failedAction =
        Action1 { throwable: Throwable? ->
            Log.e(
                "Developer",
                "Traer tasks fallo",
                throwable
            )
        }

    val successAction = Action1 { tasks: List<TasksDto> ->
        val taskslist = tasks.toMutableList()

        binding!!.postRecyclerView.adapter = TasksAdapter(
            requireContext(),
            taskslist
        )
        binding!!.postRecyclerView.setHasFixedSize(true)
    }
    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTasksBinding.inflate(inflater, container, false)
        var tasks_connect: TaskInterface? =
            RetrofitGenerator().getInstanceTasks()?.create(
                TaskInterface::class.java)
        tasks_connect!!.getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.from(ContextCompat.getMainExecutor(requireContext())))
            .subscribe(successAction,failedAction)

        return binding!!.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.tasksFragment=this
    }



    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}