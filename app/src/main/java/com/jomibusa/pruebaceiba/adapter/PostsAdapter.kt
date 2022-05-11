package com.jomibusa.pruebaceiba.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jomibusa.pruebaceiba.databinding.ItemPostBinding
import com.jomibusa.pruebaceiba.data.model.Post

class PostsAdapter : RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {

    private var postList: List<Post> = listOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PostsAdapter.PostsViewHolder {
        val binding =
            ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostsAdapter.PostsViewHolder, position: Int) {
        val item = postList[position]
        holder.bind(item)
    }

    override fun getItemCount() = postList.size

    fun setListPosts(postList: List<Post>) {
        this.postList = postList
    }

    inner class PostsViewHolder(
        private val binding: ItemPostBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private var post: Post? = null

        fun bind(post: Post) {

            this.post = post

            binding.apply {
                textViewTitle.text = post.title
                textViewBody.text = post.body
            }
        }
    }

}