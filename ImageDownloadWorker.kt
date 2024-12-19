import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.net.URL
import java.io.File
import java.io.FileOutputStream

class ImageDownloadWorker(appContext: Context, workerParams: WorkerParameters) : Worker(appContext, workerParams) {
    override fun doWork(): Result {
        val imageUrl = inputData.getString("IMAGE_URL") ?: return Result.failure()

        return try {
            val url = URL(imageUrl)
            val connection = url.openConnection()
            connection.connect()

            val input = connection.getInputStream()
            val file = File(applicationContext.filesDir, "downloaded_image.jpg")
            val output = FileOutputStream(file)

            input.use { inputStream ->
                output.use { fileOutputStream ->
                    inputStream.copyTo(fileOutputStream)
                }
            }

            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}
