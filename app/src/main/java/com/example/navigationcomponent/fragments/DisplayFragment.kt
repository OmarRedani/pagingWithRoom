package com.example.navigationcomponent.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.navigationcomponent.R
import com.example.navigationcomponent.adapters.UserAdapter
import com.example.navigationcomponent.viewmodel.UserViewModel
import com.example.navigationcomponent.viewmodel.UserViewModelFactory
import com.example.roomapp.data.UserDao
import com.example.roomapp.data.UserDatabase
import com.example.roomapp.repository.UserRepository
import kotlinx.coroutines.flow.collectLatest

class DisplayFragment : Fragment() {


    private val pagingAdapter by lazy { UserAdapter()}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_display, container, false)
        val recyclerView:RecyclerView = view.findViewById(R.id.recyclerview)

        val userDao = UserDatabase.getDatabase(requireContext()).userDao()
        val repository = UserRepository(userDao)
        val factory = UserViewModelFactory(repository)
        val viewModel = ViewModelProvider(this,factory).get(UserViewModel::class.java)

     //

        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = pagingAdapter
            hasFixedSize()
        }

        lifecycleScope.launchWhenStarted {
            viewModel.readAllUsers().collectLatest {
                Log.d("TAG", "onCreateView: collect")
                pagingAdapter.submitData(it)
            }
        }
        viewModel.addUser()
        return view
    }
}