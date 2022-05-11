package com.jomibusa.pruebaceiba.presenter

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jomibusa.pruebaceiba.adapter.PostsAdapter
import com.jomibusa.pruebaceiba.data.network.RetrofitManager
import com.jomibusa.pruebaceiba.interfaces.PostsUserCT
import com.jomibusa.pruebaceiba.data.model.Post
import com.jomibusa.pruebaceiba.data.model.User
import com.jomibusa.pruebaceiba.data.roomDatabase.database.UserDatabase
import com.jomibusa.pruebaceiba.data.roomDatabase.entities.PostEntity
import com.jomibusa.pruebaceiba.data.roomDatabase.entities.UserEntity

class PostsUserPT(private val view: PostsUserCT.View) : PostsUserCT.Presenter {

    private lateinit var retrofitManager: RetrofitManager

    private lateinit var recyclerViewUsers: RecyclerView
    private lateinit var postAdapter: PostsAdapter

    private lateinit var context: Context

    private lateinit var database: UserDatabase

    override fun start(context: Context, recyclerView: RecyclerView, userID: Int) {

        this.recyclerViewUsers = recyclerView
        this.context = context

        database = UserDatabase(context)
        retrofitManager = RetrofitManager()

        validateDB(userID)
    }

    private fun validateDB(userID: Int) {
        val dataPosts = database.getUserDao().getAllPost(userID)
        if (dataPosts != null && dataPosts.isNotEmpty()) {
            val listPosts = parseEntityToModel(dataPosts)
            setAdapter(listPosts)
            view.showLoading(false)
            view.showNotData(false)
        } else {
            getListPosts(userID)
        }
    }

    private fun getListPosts(userID: Int) {
        retrofitManager.getListPosts(userID.toString()) {
            if (it != null && it.isNotEmpty()) {
                setAdapter(it)
                insertPostsDatabase(it, userID)
                view.showLoading(false)
                view.showNotData(false)
            } else {
                view.showLoading(false)
                view.showNotData(true)
            }
        }
    }

    private fun parseEntityToModel(listPosts: List<PostEntity>): List<Post> {
        val listPostModel: MutableList<Post> = mutableListOf()
        for (i in listPosts.indices) {
            val post = Post(
                postID = listPosts[i].id,
                userID = listPosts[i].userId,
                title = listPosts[i].title,
                body = listPosts[i].body
            )
            listPostModel.add(post)
        }

        return listPostModel
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

    private fun insertPostsDatabase(listPosts: List<Post>, userID: Int) {
        for (i in listPosts.indices) {
            database.getUserDao().insertAllPost(
                PostEntity(
                    listPosts[i].postID,
                    listPosts[i].userID,
                    listPosts[i].title,
                    listPosts[i].body
                )
            )
        }
        val data = database.getUserDao().getAllPost(userID)
        data?.forEach {
            println(it)
        }
    }

}