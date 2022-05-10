package com.jomibusa.pruebaceiba.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.jomibusa.pruebaceiba.databinding.ItemUserBinding
import com.jomibusa.pruebaceiba.model.User

class UserAdapter(private val onClick: (User) -> Unit) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>(), Filterable {

    private var userList: List<User> = listOf()
    private var userListFilter: List<User> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding =
            ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = userListFilter[position]
        holder.bind(item)
    }

    override fun getItemCount() = userListFilter.size

    fun setListUsers(userList: List<User>) {
        this.userList = userList
        this.userListFilter = userList
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    userListFilter = userList
                } else {
                    var resultList = mutableListOf<User>()
                    resultList = processFilterName(charSearch, resultList)
                    userListFilter = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = userListFilter
                //filterResults.count = userListFilter.size
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                userListFilter = results?.values as List<User>
                notifyDataSetChanged()
            }
        }
    }

    private fun processFilterName(
        charSearch: String,
        resultList: MutableList<User>
    ): MutableList<User> {
        for (user in userList) {
            if (user.name.lowercase().contains(charSearch.lowercase())) {
                resultList.add(user)
            }
        }
        return resultList
    }

    inner class UserViewHolder(
        private val binding: ItemUserBinding,
        private val onClick: (User) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        private var user: User? = null

        init {
            binding.textViewPost.setOnClickListener { user?.let { onClick(it) } }
        }

        fun bind(user: User) {

            this.user = user

            binding.apply {
                textViewNameUser.text = user.name
                textViewPhone.text = user.phone
                textViewEmail.text = user.email
            }
        }
    }

}