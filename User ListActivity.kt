import android.os.Bundle
   import androidx.activity.viewModels
   import androidx.appcompat.app.AppCompatActivity
   import androidx.recyclerview.widget.LinearLayoutManager
   import androidx.recyclerview.widget.RecyclerView
   import dagger.hilt.android.AndroidEntryPoint
   import javax.inject.Inject

   @AndroidEntryPoint
   class UserListActivity : AppCompatActivity() {
       private lateinit var recyclerView: RecyclerView
       private lateinit var userAdapter: UserAdapter

       @Inject
       lateinit var userRepository: UserRepository

       override fun onCreate(savedInstanceState: Bundle?) {
           super.onCreate(savedInstanceState)
           setContentView(R.layout.activity_user_list)

           recyclerView = findViewById(R.id.recycler_view)
           recyclerView.layoutManager = LinearLayoutManager(this)

           lifecycleScope.launch {
               userRepository.fetchUsers()
               val users = userRepository.getAllUsers()
               userAdapter = UserAdapter(users)
               recyclerView.adapter = userAdapter
           }
       }
   }
