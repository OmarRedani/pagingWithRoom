package com.example.roomapp.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.roomapp.data.UserDao
import com.example.roomapp.model.User

class UserRepository(private val userDao: UserDao) {

    val readAllData = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = { userDao.readAllUsers() }
    ).flow

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }

}