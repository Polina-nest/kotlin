import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date

class CameraActivity : AppCompatActivity() {

    private lateinit var currentPhotoFile: File
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        imageView = findViewById(R.id.imageView)
        val takePictureButton = findViewById<Button>(R.id.takePictureButton)

        takePictureButton.setOnClickListener { dispatchTakePictureIntent() }
    }

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            currentPhotoFile = createImageFile()
            val photoURI: Uri = FileProvider.getUriForFile(
                this,
                "com.example.android.fileprovider",
                currentPhotoFile
            )
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            imageView.setImageURI(Uri.fromFile(currentPhotoFile))
        }
    }

    private fun createImageFile(): File {
        val storageDir = getExternalFilesDir(null)?.let {
            File(it, "photos")
        }
        return File.createTempFile(
            "${getCurrentDateTime()}.jpg",
            ".jpg",
            storageDir
        ).apply {
            currentPhotoFile = this
        }
    }

    private fun getCurrentDateTime(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd_HH-mm-ss")
        return dateFormat.format(Date())
    }

    companion object {
        private const val REQUEST_IMAGE_CAPTURE = 1
    }
}
