import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File
import java.text.SimpleDateFormat
import java.util.Collections
import java.util.Comparator

class ListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private val photoItems = mutableListOf<PhotoItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        loadPhotos()
    }

    private fun loadPhotos() {
        val directory = File(getExternalFilesDir(null), "photos")
        val files = directory.listFiles { _, name -> name.endsWith(".jpg") }

        if (files != null) {
            for (file in files) {
                val filename = file.nameWithoutExtension
                val dateTime = filename.substring(0, filename.lastIndexOf("_"))
                photoItems.add(PhotoItem(dateTime, file.absolutePath))
            }

            Collections.sort(photoItems, compareByDescending { it.dateTime })

            val adapter = PhotoAdapter(photoItems)
            recyclerView.adapter = adapter
        }
    }

    private inner class PhotoAdapter(private val photoItems: List<PhotoItem>) :
        RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
            return PhotoViewHolder(view)
        }

        override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
            val photoItem = photoItems[position]
            holder.textViewDateTime.text = photoItem.dateTime
            // Загрузить изображение в ImageView
        }

        override fun getItemCount(): Int = photoItems.size

        inner class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val textViewDateTime: TextView = itemView.findViewById(R.id.textViewDateTime)
            val imageViewPhoto: ImageView = itemView.findViewById(R.id.imageViewPhoto)
        }
    }

    private data class PhotoItem(val dateTime: String, val path: String)
}
