package com.swapnesh.cgpoc.presentation.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.swapnesh.cgpoc.data.model.Products
import com.swapnesh.cgpoc.databinding.ProductItemBinding
import com.swapnesh.cgpoc.doamin.OnItemClickListener


class ProductAdapter( private val data: ArrayList<Products>,
                      private val listener: OnItemClickListener
):RecyclerView.Adapter<ProductAdapter.ProductHolder>() {

    class ProductHolder(val binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val binding = ProductItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductHolder(binding)
    }



    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val product = data[position]
        holder.binding.image1.load(product.images.get(0))
        holder.binding.itemname.text = product.title
        holder.binding.itemdesc.text = product.description
        holder.binding.itemprice.text = "$ "+product.price.toString()

        holder.binding.icDelete.setOnClickListener {
            listener.onDeleteClick(position,product)
        }

        holder.binding.cardview.setOnClickListener {
            listener.onItemClick(position,product)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

}