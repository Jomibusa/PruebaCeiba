package com.jomibusa.pruebaceiba.interfaces

import android.content.Context
import androidx.recyclerview.widget.RecyclerView

interface PostsUserCT {

    interface Presenter {

        fun start(context: Context, recyclerView: RecyclerView, userID: Int)

    }

    interface View {

        fun showListPosts(show: Boolean)

        fun showNotData(show: Boolean)

        fun showLoading(show: Boolean)

    }

}