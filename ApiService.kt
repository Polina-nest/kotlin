import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("users") // Замените на ваш API-эндпоинт
    fun getUsers(): Call<List<User>>
}
