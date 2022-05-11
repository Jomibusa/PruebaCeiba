package com.jomibusa.pruebaceiba.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import com.jomibusa.pruebaceiba.base.BaseActivity
import com.jomibusa.pruebaceiba.databinding.ActivityMainBinding
import com.jomibusa.pruebaceiba.interfaces.MainActivityCT
import com.jomibusa.pruebaceiba.data.model.User
import com.jomibusa.pruebaceiba.presenter.MainActivityPT

class MainActivity : BaseActivity(), MainActivityCT.View, SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityMainBinding

    private lateinit var presenter: MainActivityCT.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = MainActivityPT(this)

        presenter.start(this, binding.recyclerViewUsers)

        binding.searchViewUser.setOnQueryTextListener(this@MainActivity)

        val closeButton =
            binding.searchViewUser.findViewById<View>(androidx.appcompat.R.id.search_close_btn)
        closeButton.setOnClickListener {
            binding.searchViewUser.setQuery("", false)
            showListUsers(true)
            showNotData(false)
        }

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        presenter.doFilter(newText)
        return false
    }

    override fun showListUsers(show: Boolean) {
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

    override fun navigateToPosts(user: User) {
        val intent = Intent(this, PostsUser::class.java).putExtra("USER", user)
        startActivity(intent)
    }
}