package com.geminiboy.note

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.geminiboy.note.databinding.FragmentHomeBinding
import com.geminiboy.note.dbroom.DatabaseNote
import com.geminiboy.note.dbroom.EntityNote
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.ArrayList


class HomeFragment : Fragment() {
    lateinit var binding : FragmentHomeBinding
    lateinit var Vm : HomeViewModel
    lateinit var pref : SharedPreferences
    lateinit var adapter: HomeAdapter
    lateinit var note : ArrayList<EntityNote>
    var datbasenote :DatabaseNote? =null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = requireActivity().getSharedPreferences("Berhasil", Context.MODE_PRIVATE)!!
        val fullname = pref.getString("username", "username")
        binding.welcome.text = "Welcome, $fullname!"
        Log.d("Homescreen", "Username : $fullname")

        Vm = ViewModelProvider(this).get(HomeViewModel::class.java)
        //ViewModel
        adapter = HomeAdapter(requireActivity(),ArrayList())

        binding.rvMain.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvMain.adapter = adapter

        //LiveData
        Vm = ViewModelProvider(this).get(HomeViewModel::class.java)
        Vm.getNoteObservers().observe(viewLifecycleOwner, Observer {
            adapter.setNotes(it as ArrayList<EntityNote>)
        })
        //Room
        datbasenote = DatabaseNote.getInstance(requireContext())
        //Add Note
        binding.floatingActionButton.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_homeFragment_to_tambahNoteFragment)

        }

        binding.btnLogout.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_homeFragment_to_loginFragment)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.item_menu_filter, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.ascending -> {
                getNoteAscend()
                return true
            }
            R.id.descending -> {
                getNoteDescend()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    fun getNoteAscend() {
        GlobalScope.launch {
            var data = datbasenote?.noteDao()?.getNoteASC()
            activity?.runOnUiThread {
                adapter = HomeAdapter(requireActivity(),data!!)
                binding.rvMain.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                binding.rvMain.adapter = adapter
            }
        }
    }

    fun getNoteDescend() {
        GlobalScope.launch {
            var data = datbasenote?.noteDao()?.getDataNote()
            activity?.runOnUiThread {
                adapter = HomeAdapter(requireActivity(),data!!)
                binding.rvMain.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                binding.rvMain.adapter = adapter
            }
        }
    }





    override fun onStart() {
                super.onStart()
                GlobalScope.launch {
                    var data = datbasenote?.noteDao()?.getDataNote()
                    activity?.runOnUiThread {
                        adapter = HomeAdapter(requireActivity(), data!!)
                        binding.rvMain.layoutManager =
                            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                        binding.rvMain.adapter = adapter
                    }
                }
            }


        }


