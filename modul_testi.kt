//testDownloadImageSuccess: Проверяет успешную загрузку изображения и его сохранение.
//testDownloadImageFailure: Проверяет, что изображение не сохраняется при ошибке сети.
//testSaveImageToInternalStorage: Проверяет успешное сохранение изображения во внутреннее хранилище.
//testSaveImageToInternalStorageFailure: Проверяет, что изображение не сохраняется при ошибке записи.
//testValidUrl и testInvalidUrl: Проверяют корректность URL.


import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.yourapp.MainActivity
import okhttp3.*
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import java.io.ByteArrayInputStream
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private lateinit var activity: MainActivity
    private lateinit var mockClient: OkHttpClient

    @Before
    fun setUp() {
        activity = MainActivity()
        mockClient = mock(OkHttpClient::class.java)
    }

    @Test
    fun testDownloadImageSuccess() {
        val url = "http://example.com/image.jpg"
        val responseBody = ResponseBody.create(MediaType.parse("image/jpeg"), ByteArray(0))
        val response = Response.Builder()
            .code(200)
            .message("OK")
            .request(Request.Builder().url(url).build())
            .protocol(Protocol.HTTP_1_1)
            .body(responseBody)
            .build()

        `when`(mockClient.newCall(any())).thenReturn(mock(Call::class.java))
        `when`(mockClient.newCall(any()).execute()).thenReturn(response)

        activity.downloadImage(url)

        // Проверка, что изображение сохраняется
        val file = File(activity.filesDir, "downloaded_image.jpg")
        assertTrue(file.exists())
    }

    @Test
    fun testDownloadImageFailure() {
        val url = "http://example.com/image.jpg"
        val call = mock(Call::class.java)
        `when`(mockClient.newCall(any())).thenReturn(call)
        `when`(call.execute()).thenThrow(IOException("Network error"))

        activity.downloadImage(url)

        // Проверка, что изображение не сохранилось
        val file = File(activity.filesDir, "downloaded_image.jpg")
        assertTrue(!file.exists())
    }

    @Test
    fun testSaveImageToInternalStorage() {
        val inputStream = ByteArrayInputStream(byteArrayOf(1, 2, 3, 4, 5))

        activity.saveImageToInternalStorage(inputStream)

        // Проверка, что изображение сохранилосьб
        val file = File(activity.filesDir, "downloaded_image.jpg")
        assertTrue(file.exists())
    }

    @Test
    fun testSaveImageToInternalStorageFailure() {
  
        val inputStream = mock(InputStream::class.java)
        `when`(inputStream.read(any(ByteArray::class.java))).thenThrow(IOException("Write error"))

        activity.saveImageToInternalStorage(inputStream)

        // Проверка, что изображение не сохранено
        val file = File(activity.filesDir, "downloaded_image.jpg")
        assertTrue(!file.exists())
    }

    @Test
    fun testValidUrl() {
        val validUrl = "http://example.com/image.jpg"
        assertTrue(activity.isValidUrl(validUrl))
    }

    @Test
    fun testInvalidUrl() {
        val invalidUrl = "invalid-url"
        assertTrue(!activity.isValidUrl(invalidUrl))
    }
}
