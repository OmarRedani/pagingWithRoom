package com.example.navigationcomponent.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.navigationcomponent.R
import com.example.roomapp.model.User

class UserAdapter : PagingDataAdapter<User, UserAdapter.UserNameViewHolder>(UserDiff()) {

    override fun onBindViewHolder(holder: UserNameViewHolder, position: Int) {
        getItem(position)?.let { User -> holder.bind(User) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserNameViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return UserNameViewHolder(view)
    }

    inner class UserNameViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        private val textView : TextView = itemView.findViewById(R.id.name_id)
        fun bind(user: User) {
            textView.text = user.firstName
        }
    }

    class UserDiff : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean = oldItem == newItem
    }
}