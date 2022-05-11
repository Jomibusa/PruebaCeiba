package com.jomibusa.pruebaceiba.presenter

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jomibusa.pruebaceiba.adapter.UserAdapter
import com.jomibusa.pruebaceiba.data.network.RetrofitManager
import com.jomibusa.pruebaceiba.interfaces.MainActivityCT
import com.jomibusa.pruebaceiba.data.model.User
import com.jomibusa.pruebaceiba.data.roomDatabase.database.UserDatabase
import com.jomibusa.pruebaceiba.data.roomDatabase.entities.UserEntity

class MainActivityPT(private val view: MainActivityCT.View) : MainActivityCT.Presenter {

    private lateinit var retrofitManager: RetrofitManager

    private lateinit var recyclerViewUsers: RecyclerView
    private lateinit var userAdapter: UserAdapter

    private lateinit var context: Context

    private lateinit var database: UserDatabase

    override fun start(context: Context, recyclerView: RecyclerView) {

        this.recyclerViewUsers = recyclerView
        this.context = context

        database = UserDatabase(context)
        retrofitManager = RetrofitManager()
        validateDB()

    }

    private fun validateDB() {
        val dataUsers = database.getUserDao().getAllUser()
        if (dataUsers != null && dataUsers.isNotEmpty()) {
            val listUser = parseEntityToModel(dataUsers)
            setAdapter(listUser)
            view.showLoading(false)
            view.showNotData(false)
        } else {
            getListUsers()
        }
    }

    private fun getListUsers() {
        retrofitManager.getListUsers {
            if (it != null && it.isNotEmpty()) {
                setAdapter(it)
                insertUsersDatabase(it)
                view.showLoading(false)
                view.showNotData(false)
            } else {
                view.showLoading(false)
                view.showNotData(true)
            }
        }
    }

    private fun parseEntityToModel(listUsers: List<UserEntity>): List<User> {
        val listUserModel: MutableList<User> = mutableListOf()
        for (i in listUsers.indices) {
            val user = User(
                userID = listUsers[i].userID,
                name = listUsers[i].name,
                phone = listUsers[i].phone,
                email = listUsers[i].email
            )
            listUserModel.add(user)
        }

        return listUserModel
    }

    private fun setAdapter(listUsers: List<User>) {
        userAdapter = UserAdapter { user ->
            view.navigateToPosts(user)
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

    private fun insertUsersDatabase(listUsers: List<User>) {
        for (i in listUsers.indices) {
            database.getUserDao().insertAllUser(
                UserEntity(
                    listUsers[i].userID,
                    listUsers[i].name,
                    listUsers[i].email,
                    listUsers[i].phone
                )
            )
        }
        val data = database.getUserDao().getAllUser()
        data?.forEach {
            println(it)
        }
    }
}