package com.example.roomapp.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.navigationcomponent.data.PagingSource
import com.example.roomapp.data.UserDao
import com.example.roomapp.model.User

class UserRepository(private val userDao: UserDao) {

    val readAllData = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = { PagingSource(userDao) }
    ).flow

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }

}