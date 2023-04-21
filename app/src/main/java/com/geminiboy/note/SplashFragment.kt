package com.geminiboy.note

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.geminiboy.note.databinding.FragmentSplashBinding


class SplashFragment : Fragment() {
    lateinit var binding: FragmentSplashBinding
    lateinit var prefSplash: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prefSplash = requireActivity().getSharedPreferences("Berhasil", Context.MODE_PRIVATE)!!
        val getUser = prefSplash.getString("username", "")

        if(getUser != "") {
            var dataUsername = prefSplash.getString("username", "username")
            var bundle = Bundle()
            bundle.putString("username", dataUsername)

            Handler().postDelayed({
                Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_homeFragment, bundle)
            }, 3000)
        }
        else {
            Handler().postDelayed({
                Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_loginFragment)
            }, 3000)
        }
    }




}



