kotlin
   import android.content.Context
   import androidx.room.Database
   import androidx.room.Room
   import androidx.room.RoomDatabase
   import dagger.Module
   import dagger.Provides
   import dagger.hilt.InstallIn
   import dagger.hilt.components.SingletonComponent
   import retrofit2.Retrofit
   import retrofit2.converter.gson.GsonConverterFactory
   import javax.inject.Singleton

   @Database(entities = [User Entity::class], version = 1)
   abstract class AppDatabase : RoomDatabase() {
       abstract fun userDao(): UserDao
   }

   @Module
   @InstallIn(SingletonComponent::class)
   object AppModule {

       @Provides
       @Singleton
       fun provideRetrofit(): Retrofit {
           return Retrofit.Builder()
               .baseUrl("https://example.com/api/") // Замените на ваш базовый URL
               .addConverterFactory(GsonConverterFactory.create())
               .build()
       }

       @Provides
       @Singleton
       fun provideApiService(retrofit: Retrofit): ApiService {
           return retrofit.create(ApiService::class.java)
       }

       @Provides
       @Singleton
       fun provideDatabase(context: Context): AppDatabase {
           return Room.databaseBuilder(
               context,
               AppDatabase::class.java,
               "app_database"
           ).build()
       }

       @Provides
       @Singleton
       fun provideUser Dao(database: AppDatabase): UserDao {
           return database.userDao()
       }
   }

   interface ApiService {
       @GET("users") // Замените на ваш API-эндпоинт
       fun getUsers(): Call<List<UserEntity>>
   }

   data class UserEntity(
       @PrimaryKey val id: Int,
       val name: String,
       val email: String
   )

   @Dao
   interface UserDao {
       @Insert
       suspend fun insert(user: UserEntity)

       @Query("SELECT * FROM UserEntity")
       suspend fun getAllUsers(): List<UserEntity>
   }
