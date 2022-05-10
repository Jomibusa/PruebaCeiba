package com.jomibusa.pruebaceiba.interfaces

import androidx.recyclerview.widget.RecyclerView
import com.jomibusa.pruebaceiba.model.User

interface MainActivityCT {

    interface Presenter {

        fun start(recyclerView: RecyclerView)

        fun doFilter(search: String?)

    }

    interface View {

        fun showListUsers(show: Boolean)

        fun showNotData(show: Boolean)

        fun showLoading(show: Boolean)

        fun navigateToPosts(user: User)

    }

}