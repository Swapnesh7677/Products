package com.swapnesh.cgpoc.presentation.ui

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.swapnesh.cgpoc.data.model.Products
import com.swapnesh.cgpoc.databinding.ActivityMainBinding
import com.swapnesh.cgpoc.doamin.OnItemClickListener
import com.swapnesh.cgpoc.presentation.adapter.ProductAdapter
import com.swapnesh.cgpoc.presentation.viewmodel.ProductViewModel
import com.swapnesh.cgpoc.utils.extension.beGone
import com.swapnesh.cgpoc.utils.extension.beVisible
import com.swapnesh.cgpoc.utils.extension.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.internal.notify
import okhttp3.internal.notifyAll


@AndroidEntryPoint
class MainActivity : AppCompatActivity(),OnItemClickListener {

    private  val productViewModel: ProductViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()


        productViewModel.getProductLists()

        lifecycleScope.launch(Dispatchers.Main) {
            productViewModel.productlistDataFlow.collect { data ->
               println(data.products.get(0).title)
                println(data.products.size)
                binding.productRV.apply {
                    //layoutManager =  LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
                     layoutManager = GridLayoutManager(this@MainActivity, 2)
                    val productAdapter = ProductAdapter(data.products,this@MainActivity)
                    adapter = ProductAdapter(data.products,this@MainActivity)
                    productAdapter.notifyDataSetChanged()

                }
            }
        }

        binding.toolbarTextView.setOnClickListener {
           // startActivity(Intent(this@MainActivity, PersonsActivity::class.java))
            startActivity(Intent(this@MainActivity, UserActivity::class.java))
        }


        lifecycleScope.launch(Dispatchers.IO) {
            productViewModel.productdelete.collect { data ->
                println(data)
                println(data.isDeleted)

                if(data.id == 1){
                    (data.id)?.let{
                        binding.productRV.adapter?.notifyItemRemoved(0)
                    }
                }else{
                    (data.id?.minus(1))?.let { binding.productRV.adapter?.notifyItemRemoved(it) }
                }


            }
        }

    }
    private fun init() {
        binding.toolbar.setTitle("Products")
        binding.toolbar.setTitleTextColor(Color.WHITE)


        productViewModel._wating.value = false
        productViewModel.waitForServer.observe(this) {
            if (it) {
                binding.progressBar.beVisible()

            } else {
                binding.progressBar.beGone()

            }
        }
        productViewModel.apiErrorToast.observe(this) { error ->
            toast(error.toString())
        }
    }

    override fun onDeleteClick(position: Int, model: Products) {
        productViewModel.deleteProduct(model.id)
    }

    override fun onItemClick(position: Int, model: Products) {
        val intent = Intent(this@MainActivity, ProductDetilsActivity::class.java)
        intent.putExtra("Product", model)
        startActivity(intent)

    }

}