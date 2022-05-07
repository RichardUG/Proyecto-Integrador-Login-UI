package com.example.ietiapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.ietiapp.databinding.FragmentSecondBinding
import com.example.ietiapp.generators.RetrofitGenerator
import com.example.ietiapp.interfaces.TaskInterface
import com.example.ietiapp.model.OrederViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

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
            Log.e("tareas", tasks_connect.getAll().toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}