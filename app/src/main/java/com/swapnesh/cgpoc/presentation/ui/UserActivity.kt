package com.swapnesh.cgpoc.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.swapnesh.cgpoc.presentation.ui.ui.theme.CGPOCTheme
import com.swapnesh.cgpoc.presentation.viewmodel.UsersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserActivity : ComponentActivity() {
    private val userViewModel: UsersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        userViewModel.getAllUsers()
        setContent {
            CGPOCTheme {
                PersonList(userViewModel, { finish() })
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonList(userViewModel: UsersViewModel, onBackClick: () -> Unit) {

    val users by userViewModel.users

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("All Users") },
                navigationIcon = {
                    IconButton(onClick = { onBackClick  }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }

    ){it
        LazyColumn (modifier = Modifier.padding(it)) {
            items(users){ person ->
                var name = person.firstName + person.lastName
                PersonInfoCard(
                    imageResId = person.image!!,
                    name = name,
                    email = person.email!!,
                    mobile = person.phone!!,
                    birthdate = person.birthDate!!
                )
            }
        }
    }





}



@Composable
fun PersonInfoCard(
    imageResId: String,
    name: String,
    email: String,
    mobile: String,
    birthdate: String
) {
    Card(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = imageResId),
                contentDescription = "Person Image",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(text = name, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Text(text = email, fontSize = 16.sp)
                Text(text = mobile, fontSize = 16.sp)
                Text(text = birthdate, fontSize = 16.sp)
            }
        }
    }
}

