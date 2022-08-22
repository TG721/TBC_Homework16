package com.example.tbc_homework16.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tbc_homework16.databinding.LoaderBinding

class LoaderAdapter(private val retryCallBack: () -> Unit ) : LoadStateAdapter<LoaderAdapter.UserLoaderStateViewHolder>() {

    inner class UserLoaderStateViewHolder(
        private val binding: LoaderBinding,
        private val retry: () -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState) {
            binding.apply {
                if (loadState is LoadState.Error) {
                    TVError.text = loadState.error.localizedMessage
                }
                progressbar.visible(loadState is LoadState.Loading)
                TVRetry.visible(loadState is LoadState.Error)
                TVError.visible(loadState is LoadState.Error)
                TVRetry.setOnClickListener {
                    retry()
                }
                progressbar.visibility = View.VISIBLE
            }
        }
    }

    override fun onBindViewHolder(holder: UserLoaderStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState,
    ) = UserLoaderStateViewHolder(
        LoaderBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        retryCallBack
    )
}

fun View.visible(isVisible: Boolean) {
    if (isVisible) visibility = View.VISIBLE
    else visibility = View.GONE
}