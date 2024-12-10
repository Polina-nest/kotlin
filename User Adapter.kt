import android.view.LayoutInflater
   import android.view.View
   import android.view.ViewGroup
   import android.widget.TextView
   import androidx.recyclerview.widget.RecyclerView

   class UserAdapter(private val userList: List<UserEntity>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
       override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
           val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
           return UserViewHolder(view)
       }

       override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
           val user = userList[position]
           holder.nameTextView.text = user.name
           holder.emailTextView.text = user.email
       }

       override fun getItemCount(): Int = userList.size

       class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
           val nameTextView: TextView = itemView.findViewById(R.id.text_view_name)
           val emailTextView: TextView = itemView.findViewById(R.id.text_view_email)
       }
   }
