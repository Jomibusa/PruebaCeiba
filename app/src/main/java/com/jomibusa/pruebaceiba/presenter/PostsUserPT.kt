package com.jomibusa.pruebaceiba.presenter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jomibusa.pruebaceiba.adapter.PostsAdapter
import com.jomibusa.pruebaceiba.data.network.RetrofitManager
import com.jomibusa.pruebaceiba.interfaces.PostsUserCT
import com.jomibusa.pruebaceiba.data.model.Post

class PostsUserPT(private val view: PostsUserCT.View) : PostsUserCT.Presenter {

    private lateinit var retrofitManager: RetrofitManager

    private lateinit var recyclerViewUsers: RecyclerView
    private lateinit var postAdapter: PostsAdapter

    override fun start(recyclerView: RecyclerView, userID: Int) {
        retrofitManager = RetrofitManager()
        this.recyclerViewUsers = recyclerView

        retrofitManager.getListPosts(userID.toString()) {
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

    private fun setAdapter(listPosts: List<Post>) {
        postAdapter = PostsAdapter()
        postAdapter.setListPosts(listPosts)
        recyclerViewUsers.apply {
            layoutManager = LinearLayoutManager(context)
            isNestedScrollingEnabled = false
            adapter = postAdapter
        }
        view.showListPosts(true)
    }

}