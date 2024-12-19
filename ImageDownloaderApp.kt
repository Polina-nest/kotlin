package com.example.imagedownloader

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.outlinedTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ImageDownloaderApp() {
    val viewModel = remember { ImageDownloaderViewModel() }
    
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Image Downloader", style = MaterialTheme.typography.h5)

        var imageUrl by remember { mutableStateOf("") }
        OutlinedTextField(
            value = imageUrl,
            onValueChange = { imageUrl = it },
            label = { Text("Enter Image URL") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { viewModel.downloadImage(imageUrl) }) {
            Text("Download Image")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(viewModel.images) { image ->
                Card(modifier = Modifier.padding(8.dp)) {
                    Image(bitmap = image.asImageBitmap(), contentDescription = null)
                }
            }
        }
    }
}
