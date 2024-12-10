import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var imageUrlEditText: EditText
    private lateinit var downloadButton: Button
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageUrlEditText = findViewById(R.id.imageUrlEditText)
        downloadButton = findViewById(R.id.downloadButton)
        imageView = findViewById(R.id.imageView)

        downloadButton.setOnClickListener {
            val imageUrl = imageUrlEditText.text.toString()
            if (imageUrl.isNotEmpty()) {
                downloadImage(imageUrl)
            } else {
                Toast.makeText(this, "Введите ссылку на изображение", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun downloadImage(imageUrl: String) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
              

                val imageResponse = withContext(Dispatchers.IO) {
                    RetrofitClient.apiService.downloadImage(imageUrl)
                }

                saveImageToInternalStorage(imageResponse)

                val bitmap = BitmapFactory.decodeStream(imageResponse.byteStream())
                imageView.setImageBitmap(bitmap)

            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Ошибка загрузки изображения: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveImageToInternalStorage(responseBody: ResponseBody) {
        val file = File(filesDir, "downloaded_image.jpg")
        try {
        
            FileOutputStream(file).use { outputStream ->
                outputStream.write(responseBody.bytes())
            }
            Toast.makeText(this, "Изображение сохранено", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            Toast.makeText(this, "Ошибка сохранения изображения: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
}
