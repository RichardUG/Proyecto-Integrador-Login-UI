package com.example.ietiapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.ietiapp.data.LoginDto
import com.example.ietiapp.data.TokenDto
import com.example.ietiapp.data.UserDto
import com.example.ietiapp.databinding.FragmentLoginBinding
import com.example.ietiapp.databinding.FragmentNuevoUsuarioBinding
import com.example.ietiapp.generators.RetrofitGenerator
import com.example.ietiapp.interfaces.AuthInterface
import com.example.ietiapp.interfaces.UserInterface
import com.example.ietiapp.model.OrderViewModel
import rx.functions.Action1
import rx.schedulers.Schedulers

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NuevoUsuario.newInstance] factory method to
 * create an instance of this fragment.
 */
class NuevoUsuario : Fragment() {
    // TODO: Rename and change types of parameters
    private var binding: FragmentNuevoUsuarioBinding?= null
    private val sharedViewModel: OrderViewModel by activityViewModels()

    private val failedAction =
        Action1 { throwable: Throwable? ->
            Log.e(
                "Developer",
                "Crear usuario error",
                throwable
            )
            Toast.makeText(requireContext(),"Ups! Contraseña equivocada", Toast.LENGTH_SHORT).show()
        }
    private val successAction = Action1 { response: Object ->
        Toast.makeText(requireContext(),"Usuario creado correctamente", Toast.LENGTH_SHORT).show()
        binding!!.nombresEditText.setText("")
        binding!!.apellidosEditText.setText("")
        binding!!.correoEditText.setText("")
        binding!!.contraseAEditText.setText("")
        findNavController().navigate(R.id.action_nuevoUsuario_to_Login)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentNuevoUsuarioBinding.inflate(inflater, container,false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.nuevoUsuarioFragment=this
    }

    fun crear(){
        var ieti_connect: UserInterface? =
            RetrofitGenerator().getInstanceUsers(sharedViewModel.storage.value!!)?.
            create(UserInterface::class.java)
        val nombres = binding!!.nombresEditText.text.toString()
        val apellidos = binding!!.apellidosEditText.text.toString()
        val correo = binding!!.correoEditText.text.toString()
        val contraseña = binding!!.contraseAEditText.text.toString()
        var user= UserDto(nombres,apellidos,correo,contraseña)
        ieti_connect!!.create(user)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.from(ContextCompat.getMainExecutor(requireContext())))
            .subscribe(successAction,failedAction)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NuevoUsuario.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NuevoUsuario().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}