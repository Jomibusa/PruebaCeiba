package com.jomibusa.pruebaceiba.interfaces

import androidx.recyclerview.widget.RecyclerView

interface MainActivityCT {

    interface Presenter {

        fun start(recyclerView: RecyclerView)

        fun doFilter(search: String?)

    }

    interface View {

        fun showListUsers(show: Boolean)

        fun showNotData(show: Boolean)

        fun showLoading(show: Boolean)

    }

}