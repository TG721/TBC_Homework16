package com.example.tbc_homework16.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tbc_homework16.R
import com.example.tbc_homework16.databinding.UserItemBinding
import com.example.tbc_homework16.model.UserData


class UserAdapter :
    PagingDataAdapter<UserData.Data, UserAdapter.PersonViewHolder>(UserComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int, ): PersonViewHolder {
        return PersonViewHolder(
            UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bindUser()
    }

    inner class PersonViewHolder(private val binding: UserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindUser() = with(binding) {
            val item = getItem(absoluteAdapterPosition)!!
            Glide
                .with(imgUser.context)
                .load(item.avatar.toString())
                .placeholder(R.drawable.avatar_ph)
                .centerCrop()
                .into(imgUser)
            tvEmail.text = item.email.toString()
            tvName.text = item.firstName + " " + item.lastName
        }
    }

    object UserComparator : DiffUtil.ItemCallback<UserData.Data>() {
        override fun areItemsTheSame(oldItem: UserData.Data, newItem: UserData.Data): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserData.Data, newItem: UserData.Data): Boolean {
            return oldItem == newItem
        }
    }
}