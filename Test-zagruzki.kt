import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class ImageDownloaderTest {

    @Test
    fun testDownloadImageSuccess() = runBlocking {
        val imageUrl = "https://example.com/image.jpg"
        val expectedResponseBody = MockResponseBody()

        val apiService = mockApiService(expectedResponseBody)
        val imageResponse = apiService.downloadImage(imageUrl)

        assertEquals(expectedResponseBody, imageResponse)
    }

    private fun mockApiService(expectedResponseBody: ResponseBody): ApiService {


    }
}
