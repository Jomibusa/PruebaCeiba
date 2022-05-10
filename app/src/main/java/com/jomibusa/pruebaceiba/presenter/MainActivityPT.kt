package com.jomibusa.pruebaceiba.presenter

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jomibusa.pruebaceiba.adapter.UserAdapter
import com.jomibusa.pruebaceiba.data.RetrofitManager
import com.jomibusa.pruebaceiba.interfaces.MainActivityCT
import com.jomibusa.pruebaceiba.model.User

class MainActivityPT(private val view: MainActivityCT.View) : MainActivityCT.Presenter {

    private lateinit var retrofitManager: RetrofitManager

    private lateinit var recyclerViewUsers: RecyclerView
    private lateinit var userAdapter: UserAdapter

    override fun start(recyclerView: RecyclerView) {
        retrofitManager = RetrofitManager()
        this.recyclerViewUsers = recyclerView

        retrofitManager.getListUsers {
            if (it != null && it.isNotEmpty()) {
                setAdapter(it)
                view.showLoading(false)
                view.showNotData(false)
            } else {
                view.showLoading(false)
                view.showNotData(true)
            }
        }
    }

    private fun setAdapter(listUsers: List<User>) {
        userAdapter = UserAdapter { item ->
            Log.d("TEST_USER_SELECTED", "Name: ${item.name}")
        }
        userAdapter.setListUsers(listUsers)
        recyclerViewUsers.apply {
            layoutManager = LinearLayoutManager(context)
            isNestedScrollingEnabled = false
            adapter = userAdapter
        }
        view.showListUsers(true)
    }

    override fun doFilter(search: String?) {
        userAdapter.filter.filter(search)
        if (userAdapter.itemCount <= 0) {
            view.showListUsers(false)
            view.showNotData(true)
        } else {
            view.showListUsers(true)
            view.showNotData(false)
        }
    }
}