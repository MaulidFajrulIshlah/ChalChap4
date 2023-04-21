package com.geminiboy.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.geminiboy.note.databinding.FragmentEditBinding
import com.geminiboy.note.dbroom.DatabaseNote
import com.geminiboy.note.dbroom.EntityNote
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async


class EditFragment : Fragment() {
    lateinit var binding: FragmentEditBinding
    lateinit var Vm: HomeViewModel
    var dbasenote: DatabaseNote? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Update Note
        dbasenote = DatabaseNote.getInstance(requireContext())
        Vm = ViewModelProvider(this).get(HomeViewModel::class.java)
        var getnote = arguments?.getSerializable("edit") as EntityNote
        binding.editTitle.setText(getnote.title)
        binding.editNote.setText(getnote.content)
        binding.idNote.setText(getnote.id.toString())

        binding.btnUpdate.setOnClickListener {
            editnote()
        }

    }


    private fun editnote() {

            GlobalScope.async {
                var getNote = arguments?.getSerializable("edit") as EntityNote
                var judul = binding.editTitle.text.toString()
                var isi = binding.editNote.text.toString()


                val dataInsert = EntityNote(getNote.id, judul, isi)
                Vm.update(dataInsert)

                activity?.runOnUiThread {
                    Toast.makeText(context, "Berhasil menambahkan note", Toast.LENGTH_LONG)
                }
            }
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_editFragment_to_homeFragment)

        }
    }







