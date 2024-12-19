import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.work.*
import java.net.URL
import java.io.File
import java.io.FileOutputStream

@Composable
fun ImageDownloaderScreen() {
    var url by remember { mutableStateOf("") }
    var downloadStatus by remember { mutableStateOf("") }

    Column {
        TextField(
            value = url,
            onValueChange = { url = it },
            label = { Text("Enter Image URL") }
        )
        Button(onClick = { startImageDownload(url) }) {
            Text("Download Image")
        }
        Text(downloadStatus)
    }

    // Функция для запуска загрузки изображения
    fun startImageDownload(imageUrl: String) {
        val data = Data.Builder()
            .putString("IMAGE_URL", imageUrl)
            .build()

        val workRequest = OneTimeWorkRequestBuilder<ImageDownloadWorker>()
            .setInputData(data)
            .build()

        WorkManager.getInstance().enqueue(workRequest)

        // Обновление статуса загрузки
        downloadStatus = "Download started for $imageUrl"
    }
}
