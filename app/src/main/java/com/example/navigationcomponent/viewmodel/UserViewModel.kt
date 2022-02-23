package com.example.navigationcomponent.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.roomapp.model.User
import com.example.roomapp.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    fun readAllUsers() = repository.readAllData.cachedIn(viewModelScope)

    fun addUser(){
        Log.d("TAG", "addUser: called")
        viewModelScope.launch {
            for (i in 0..10){
                val user = User(0,"Name $i")
                repository.addUser(user)
            }
        }
    }

}