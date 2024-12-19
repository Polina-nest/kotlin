/*Содержит функцию DefaultPreview, которая позволяет разработчикам видеть, 
как будет выглядеть интерфейс приложения без необходимости запускать его на устройстве или эмуляторе.*/



package com.example.imagedownloader

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ImageDownloaderApp()
}
