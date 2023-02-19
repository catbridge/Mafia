package com.example.mafia.adapter

import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mafia.databinding.ItemRoleBinding
import com.example.mafia.model.Role

class RoleViewHolder(
    private val binding: ItemRoleBinding,
    private val onDeleteRoleClicked: (Role) -> Unit
): RecyclerView.ViewHolder(binding.root)  {

    fun bind(role: Role){
        with(binding){
            roleText.text = "${role.name} - ${role.role}"

            deleteImg.setOnClickListener {
                onDeleteRoleClicked(role)
                Toast.makeText(itemView.context, "${role.name} выбывает", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}