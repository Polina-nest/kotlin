import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET
    suspend fun downloadImage(@Url imageUrl: String): ResponseBody
}
