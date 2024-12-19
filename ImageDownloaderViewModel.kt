package com.example.imagedownloader

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.io.FileOutputStream

class ImageDownloaderViewModel : ViewModel() {
    var images by mutableStateOf(listOf<Bitmap>())
        private set

    private val client = OkHttpClient()

    fun downloadImage(url: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val bitmap = loadBitmapFromUrl(url)
                if (bitmap != null) {
                    saveImageToDisk(bitmap)
                    images = images + bitmap
                }
            } catch (e: Exception) {
                // Handle exceptions (e.g. show a Toast)
            }
        }
    }

    private fun loadBitmapFromUrl(url: String): Bitmap? {
        val request = Request.Builder().url(url).build()
        client.newCall(request).execute().use { response ->
            return if (response.isSuccessful) {
                BitmapFactory.decodeStream(response.body?.byteStream())
            } else {
                null
            }
        }
    }

    private fun saveImageToDisk(bitmap: Bitmap) {
        // Save bitmap to internal storage
        val file = File(context.filesDir, "image_${System.currentTimeMillis()}.png")
        FileOutputStream(file).use { out ->
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
        }
    }
}
