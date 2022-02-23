package com.example.navigationcomponent.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.roomapp.data.UserDao
import com.example.roomapp.model.User

private const val STARTING_PAGE_INDEX = 1

class PagingSource(private val userDao: UserDao) : PagingSource<Int,User>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val position = params.key ?: STARTING_PAGE_INDEX
        Log.d("TAG", "load: position $position")
        val users = userDao.readAllUsers()
        Log.d("TAG", "load: users $users")
        return LoadResult.Page(
            data = users,
            prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
            nextKey = if (users.isNullOrEmpty()) null else position + 1
        )
    }
}