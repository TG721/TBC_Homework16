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
    PagingDataAdapter<UserData.User, UserAdapter.UserViewHolder>(User_Comparator_DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int, ): UserViewHolder {
        return UserViewHolder(
            UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bindUser()
    }

    inner class UserViewHolder(private val binding: UserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindUser() = with(binding) {
            val currentItem = getItem(absoluteAdapterPosition)!!
            Glide
                .with(imgUser.context)
                .load(currentItem.avatar)
                .placeholder(R.drawable.avatar_ph)
                .centerCrop()
                .into(imgUser)
            tvEmail.text = currentItem.email.toString()
            tvName.text = currentItem.firstName + " " + currentItem.lastName
        }
    }

    object User_Comparator_DIFF_CALLBACK : DiffUtil.ItemCallback<UserData.User>() {
        override fun areItemsTheSame(oldItem: UserData.User, newItem: UserData.User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserData.User, newItem: UserData.User): Boolean {
            return oldItem == newItem
        }
    }
}