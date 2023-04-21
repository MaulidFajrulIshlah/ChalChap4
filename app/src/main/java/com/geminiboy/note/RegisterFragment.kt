package com.geminiboy.note

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.geminiboy.note.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {
    lateinit var binding : FragmentRegisterBinding
    lateinit var pref : SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(layoutInflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = requireActivity().getSharedPreferences("Berhasil", Context.MODE_PRIVATE)!!
        binding.btnRegister.setOnClickListener {
            val name = binding.namaEditText.text.toString()
            val user = binding.usernameEditText.text.toString()
            val pass = binding.passwordEditText.text.toString()

           addAcc(name, user, pass)
            Navigation.findNavController(binding.root).navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }

    private fun addAcc(name: String, user: String, pass: String) {

        val addAkun = pref.edit()
        Log.d("Reg", "full name : $name")
        Log.d("Reg", "username : $user")
        Log.d("Reg", "password : $pass")
        addAkun.putString("full name", name)
        addAkun.putString("username", user)
        addAkun.putString("password", pass)
        addAkun.apply()
        Toast.makeText(context, "Registrasi Berhasil, silahkan ke halaman Login !", Toast.LENGTH_SHORT).show()

    }


}