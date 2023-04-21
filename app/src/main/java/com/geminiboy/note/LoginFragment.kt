package com.geminiboy.note

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.geminiboy.note.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    lateinit var binding : FragmentLoginBinding
    lateinit var pref : SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = requireActivity().getSharedPreferences("Berhasil", Context.MODE_PRIVATE)!!
        binding.btnLogin.setOnClickListener {
            val user = binding.usernameEditText.text.toString()
            val pass = binding.passwordEditText.text.toString()
            if (validationuser(user)){
                if (validationpass(pass)){
                    Toast.makeText(context, "Selamat Anda Berhasil login", Toast.LENGTH_LONG).show()
                    Navigation.findNavController(binding.root).navigate(R.id.action_loginFragment_to_homeFragment)
                } else {
                    Toast.makeText(context, "Sorry Username dan Password anda salah", Toast.LENGTH_LONG).show()

                }

                }else{
                    Toast.makeText(context, "Akun belum terdaftar", Toast.LENGTH_LONG).show()

            }

            binding.btnReg.setOnClickListener {
                Navigation.findNavController(binding.root).navigate(R.id.action_loginFragment_to_registerFragment)
            }

        }




    }

    private fun validationpass(pass: String): Boolean {

        val passwordpref = pref.getString("password", "")
        if (passwordpref != null){
            if (passwordpref.isBlank()){
                return false
            }else{
                if (passwordpref == pass){
                    return true
                }
            }
        }
        return false

    }

    private fun validationuser(user: String): Boolean {
        val userpref = pref.getString("username","")

        if (userpref != null){
            if (userpref.isBlank()){
                return false
            }else{
                if (userpref == user){
                    return true
                }
            }
        }
        return false

    }


}