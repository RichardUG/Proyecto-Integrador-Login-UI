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
import androidx.navigation.fragment.NavHostFragment
import com.example.ietiapp.data.LoginDto
import com.example.ietiapp.data.TokenDto
import com.example.ietiapp.databinding.FragmentSecondBinding
import com.example.ietiapp.generators.RetrofitGenerator
import com.example.ietiapp.interfaces.AuthInterface
import com.example.ietiapp.interfaces.TaskInterface
import com.example.ietiapp.model.OrederViewModel
import com.example.ietiapp.storage.ImplementedStorage
import com.example.ietiapp.storage.Storage
import rx.functions.Action1
import rx.schedulers.Schedulers
import java.util.*


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    lateinit var storage:Storage
    private val sharedViewModel: OrederViewModel by activityViewModels()
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    var tasks_connect: TaskInterface = RetrofitGenerator().getInstanceTasks().create(TaskInterface::class.java)

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e("tareas", sharedViewModel.tasks_connect.getAll().toString())
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonGetTareas.setOnClickListener {
            sendatuh()
            Log.e("tareas", tasks_connect.getAll().toString())
        }
    }

    fun sendatuh(){
        var implement: SharedPreferences? = requireContext().getSharedPreferences("sharedPreferences",Context.MODE_PRIVATE)
        var implementedStorage: ImplementedStorage = ImplementedStorage()
        implementedStorage.SharedPreferencesStorage(implement)
        storage = implementedStorage

        var users_connect: AuthInterface? =
            RetrofitGenerator().getInstanceUsers(storage)?.create(AuthInterface::class.java)
        val failedAction =
            Action1 { throwable: Throwable? ->
                Log.e(
                    "Developer",
                    "Auth error",
                    throwable
                )
            }
        val successAction = Action1 { tokenDto: TokenDto ->
            onAuthSuccess(
                tokenDto.token
            )
        }
        users_connect!!.login(LoginDto("richard@richard","clave1"))
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.from(ContextCompat.getMainExecutor(requireContext())))
            .subscribe(successAction,failedAction)

    }

    fun onAuthSuccess(tokenDto: String) {
        Log.d("richard",tokenDto)
        storage.saveToken(tokenDto)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}