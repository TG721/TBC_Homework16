package com.example.tbc_homework16.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbc_homework16.adapters.LoaderAdapter
import com.example.tbc_homework16.adapters.UserAdapter
import com.example.tbc_homework16.databinding.ActivityMainBinding
import com.example.tbc_homework16.network.RetrofitClient
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var userAdapter: UserAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // view model setup
        val factory = MainViewModelFactory(RetrofitClient.getUserInfo())
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

        //preparing list
        userAdapter = UserAdapter()
        binding.RVUsers.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = userAdapter.withLoadStateHeaderAndFooter(
                header = LoaderAdapter { userAdapter.retry() },
                footer = LoaderAdapter { userAdapter.retry() }
            )
        }

        observers()
    }

    private fun observers() {
        lifecycleScope.launch {
            viewModel.users.collectLatest {
                userAdapter.submitData(it)
            }
        }
    }
}
