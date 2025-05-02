package com.swapnesh.cgpoc.doamin

import android.view.View
import com.swapnesh.cgpoc.data.model.Products


interface OnItemClickListener {
    fun onDeleteClick( position: Int, model:Products)
    fun onItemClick( position: Int, model:Products)
}