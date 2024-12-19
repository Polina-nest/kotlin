package com.example.yourapp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.yourapp.ui.theme.YourAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YourAppTheme {
                // Вызов основного содержимого
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp), // Отступы вокруг содержимого
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Нестерова Полина Викторовна",
            style = MaterialTheme.typography.h1 // Использование стиля из темы
        )
        Spacer(modifier = Modifier.height(8.dp)) // Пробел между текстами
        Text(
            text = "ИКБО-35-22",
            style = MaterialTheme.typography.body1 // Использование стиля из темы
        )
    }
}
