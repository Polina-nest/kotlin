/* Он наследует ComponentActivity и переопределяет метод onCreate, чтобы установить содержимое приложения,
вызывая функцию setContent, которая инициализирует пользовательский интерфейс с помощью функции ImageDownloaderApp.

*/

package com.example.imagedownloader

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImageDownloaderApp()
        }
    }
}
