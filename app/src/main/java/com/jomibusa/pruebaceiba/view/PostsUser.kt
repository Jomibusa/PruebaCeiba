package com.jomibusa.pruebaceiba.view

import android.os.Bundle
import android.view.View
import com.jomibusa.pruebaceiba.base.BaseActivity
import com.jomibusa.pruebaceiba.databinding.ActivityPostsUserBinding
import com.jomibusa.pruebaceiba.interfaces.MainActivityCT
import com.jomibusa.pruebaceiba.interfaces.PostsUserCT
import com.jomibusa.pruebaceiba.data.model.User
import com.jomibusa.pruebaceiba.presenter.MainActivityPT
import com.jomibusa.pruebaceiba.presenter.PostsUserPT

class PostsUser : BaseActivity(), PostsUserCT.View {

    private lateinit var binding: ActivityPostsUserBinding

    private lateinit var presenter: PostsUserCT.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostsUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = intent.getSerializableExtra("USER") as User?

        if (user != null) {
            binding.apply {
                textViewNameUser.text = user.name
                textViewPhone.text = user.phone
                textViewEmail.text = user.email
            }
        }

        presenter = PostsUserPT(this)

        presenter.start(binding.recyclerViewUsers, user?.userID ?: 0)
    }

    override fun showListPosts(show: Boolean) {
        if (show) {
            binding.recyclerViewUsers.visibility = View.VISIBLE
        } else {
            binding.recyclerViewUsers.visibility = View.GONE
        }
    }

    override fun showNotData(show: Boolean) {
        if (show) {
            binding.textViewNoData.visibility = View.VISIBLE
        } else {
            binding.textViewNoData.visibility = View.GONE
        }
    }

    override fun showLoading(show: Boolean) {
        if (show) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}