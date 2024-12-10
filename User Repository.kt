import javax.inject.Inject

   class UserRepository @Inject constructor(
       private val apiService: ApiService,
       private val userDao: UserDao
   ) {
       suspend fun fetchUsers() {
           val response = apiService.getUsers().execute()
           if (response.isSuccessful && response.body() != null) {
               response.body()?.forEach { user ->
                   userDao.insert(user)
               }
           }
       }

       suspend fun getAllUsers(): List<UserEntity> {
           return userDao.getAllUsers()
       }
   }
