package com.example.ietiapp

import android.content.Context
import android.content.SharedPreferences
import android.media.session.MediaSession
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.ietiapp.data.LoginDto
import com.example.ietiapp.data.TokenDto
import com.example.ietiapp.databinding.FragmentLoginBinding
import com.example.ietiapp.generators.RetrofitGenerator
import com.example.ietiapp.interfaces.AuthInterface
import com.example.ietiapp.model.OrderViewModel
import com.example.ietiapp.storage.ImplementedStorage
import rx.functions.Action1
import rx.schedulers.Schedulers

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class Login : Fragment() {

    private var binding: FragmentLoginBinding ?= null
    private val sharedViewModel: OrderViewModel by activityViewModels()

    private val failedAction =
        Action1 { throwable: Throwable? ->
            Log.e(
                "Developer",
                "Auth error",
                throwable
            )
            Toast.makeText(requireContext(),"Ups! Contraseña equivocada", Toast.LENGTH_SHORT).show()
        }
    private val successAction = Action1 { token: TokenDto ->
        onAuthSuccess(
            token.token
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        startStorage()
        val fragmentBinding = FragmentLoginBinding.inflate(inflater, container,false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.loginFragment=this
    }

    fun startStorage() {
        var implement: SharedPreferences? = requireContext().getSharedPreferences(
            "sharedPreferences",
            Context.MODE_PRIVATE
        )
        var implementedStorage: ImplementedStorage = ImplementedStorage()
        implementedStorage.SharedPreferencesStorage(implement)
        sharedViewModel.startStorage(implementedStorage)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun onAuthSuccess(token: String) {
        sharedViewModel.storage.value!!.saveToken(token)
        findNavController().navigate(R.id.action_Login_to_Tasks)
    }

    fun login(){
        var ieti_connect: AuthInterface? =
            RetrofitGenerator().getInstanceUsers(sharedViewModel.storage.value!!)?.
            create(AuthInterface::class.java)
        ieti_connect!!.login(LoginDto(binding!!.emailEditText.text.toString()
            ,binding!!.passwordEditText.text.toString()))
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.from(ContextCompat.getMainExecutor(requireContext())))
            .subscribe(successAction,failedAction)
    }

    fun crearNuevo(){
        findNavController().navigate(R.id.action_Login_to_nuevoUsuario)
    }

   /* private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(childFragmentManager, "datePicker")
    }

    private fun onDateSelected(day: Int, month: Int, year: Int) {
        binding!!.etDate.setText("$year-$month-$day")
    }*/
}