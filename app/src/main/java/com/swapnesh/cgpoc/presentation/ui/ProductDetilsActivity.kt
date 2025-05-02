package com.swapnesh.cgpoc.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight



import androidx.compose.ui.unit.dp
import com.swapnesh.cgpoc.data.model.Products
import com.swapnesh.cgpoc.presentation.ui.ui.theme.CGPOCTheme

class ProductDetilsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

       val prod =  intent.getParcelableExtra<Products>("Product")
        println(prod?.images?.size)
        setContent {
            CGPOCTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    if (prod != null) {
                        ProductDetailsPage(prod,modifier = Modifier.padding(innerPadding))
                    }
                }
            }
        }
    }
}

@Composable
fun ProductDetailsPage(product: Products,modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        /*Image(
            painter = rememberAsyncImagePainter(model = product.images.get(0)),
            contentDescription = "product",
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
                .clip(RoundedCornerShape(8.dp))
        )*/

        ImageProductPager(product = product)


        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = product.title!!,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = product.description!!,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Price: \$${product.price}",
            style = MaterialTheme.typography.labelLarge,
            color = Color.Green
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Brand: \$${product.brand}",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = product.availabilityStatus!!,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(16.dp))

    }
}



