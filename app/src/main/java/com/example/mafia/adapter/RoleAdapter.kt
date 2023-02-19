package com.example.mafia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.mafia.databinding.ItemRoleBinding
import com.example.mafia.model.Role

class RoleAdapter(private val action: (Role) -> Unit) : ListAdapter<Role, RoleViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoleViewHolder {
        val layoutInflater = LayoutInflater.from((parent.context))
        return RoleViewHolder(
         binding = ItemRoleBinding.inflate(
             layoutInflater,
             parent,
             false
         ),
            onDeleteRoleClicked = action
        )
    }

    override fun onBindViewHolder(holder: RoleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Role>(){
            override fun areItemsTheSame(oldItem: Role, newItem: Role): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Role, newItem: Role): Boolean {
                return oldItem == newItem
            }
        }
    }

}