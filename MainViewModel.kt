import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _currentFragment = MutableLiveData<Int>()
    val currentFragment: LiveData<Int>
        get() = _currentFragment

    fun setCurrentFragment(fragmentId: Int) {
        _currentFragment.value = fragmentId
    }
}
