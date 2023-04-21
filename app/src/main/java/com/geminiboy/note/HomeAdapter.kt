package com.geminiboy.note


import android.app.Application
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.geminiboy.note.databinding.ItemListBinding
import com.geminiboy.note.dbroom.DatabaseNote
import com.geminiboy.note.dbroom.EntityNote
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.util.ArrayList


class HomeAdapter(var context: Context, var note: List<EntityNote>) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    var Note: DatabaseNote? = null

    class ViewHolder(var binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: EntityNote) {
            binding.datbasenote = item
        }

    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(note[position])
        // delete note
        holder.binding.btnDelete.setOnClickListener {
            Note = DatabaseNote.getInstance(it.context)
            GlobalScope.async {
                HomeViewModel(Application()).delete(note[position])
                Note?.noteDao()?.deleteNote(note[position])
                kotlin.run {
                    Navigation.findNavController(it).navigate(R.id.homeFragment)
                }


            }
        }
        holder.binding.btnEdit.setOnClickListener {
            var edit = Bundle()
            edit.putSerializable("edit",note[position])
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_editFragment,edit)

        }

        holder.binding.btnDetail.setOnClickListener {
            var detail = Bundle()
            detail.putSerializable("datanotes", note[position])
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_detailFragment, detail)

        }
    }


    override fun getItemCount(): Int {
        return note.size

    }


    fun setNotes(itemNote: ArrayList<EntityNote>) {
        this.note = itemNote
    }


}