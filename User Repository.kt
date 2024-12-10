import android.content.Context
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository(private val context: Context) {
    private val apiService = RetrofitClient.getApiService()
    private val userDao = AppDatabase.getInstance(context).userDao()

    fun fetchUsers() {
        apiService.getUsers().enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful && response.body() != null) {
                    response.body()?.forEach { user ->
                        val userEntity = UserEntity(user.id, user.name, user.email)
                        // Используйте корутины для вставки в базу данных
                        userDao.insert(userEntity)
                    }
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                // Обработка ошибок
            }
        })
    }
}
