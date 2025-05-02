package com.swapnesh.cgpoc.presentation.adapter


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.swapnesh.cgpoc.data.model.Products
import com.swapnesh.cgpoc.data.model.User
import com.swapnesh.cgpoc.databinding.ProductItemBinding
import com.swapnesh.cgpoc.databinding.UserItemBinding


class UsersAdapter(private val data: ArrayList<User>
):RecyclerView.Adapter<UsersAdapter.UserHolder>() {


    class UserHolder(val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(it: User) {
           binding.name.text = "${it.firstName} ${it.lastName}"
            binding.email.text = it.email
            binding.mobile.text = it.phone
            binding.birthdate.text = it.birthDate
            binding.userImage.load(it.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val binding = UserItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return UserHolder(binding)
    }



    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.bind(data.get(position))
    }

    override fun getItemCount(): Int {
        return data.size
    }

}