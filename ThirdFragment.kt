import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myapplication.MainViewModel
import com.example.myapplication.R

class ThirdFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_third, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        view.findViewById<View>(R.id.button_third).setOnClickListener {
            findNavController().navigateUp()
            viewModel.setCurrentFragment(null)
        }

        viewModel.currentFragment.observe(viewLifecycleOwner, Observer { fragmentId ->
            if (fragmentId == R.id.thirdFragment) {
                // Обновить данные в фрагменте, если необходимо
            }
        })
    }
}
