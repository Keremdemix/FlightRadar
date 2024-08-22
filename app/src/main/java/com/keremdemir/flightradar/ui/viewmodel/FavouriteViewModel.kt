
import androidx.lifecycle.viewModelScope
import com.keremdemir.flightradar.ui.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class FavouriteViewModel(private val dataStorePreferences: DataStorePreferences) : BaseViewModel() {

    private val _flightIDList = MutableStateFlow<List<String>>(emptyList())

    fun loadFavouriteFlightIDList(): List<String> {
        return dataStorePreferences.getFlightIDList()
    }

    fun addFavouriteFlightIDList(flightId: String) {
        viewModelScope.launch {
            val updatedList = _flightIDList.value.toMutableList().apply {
                add(flightId)
            }
            dataStorePreferences.saveFlightIDList(updatedList)
            _flightIDList.value = updatedList
        }
    }

    fun removeFavouriteFlightID(flightID: String) {
        viewModelScope.launch {
            val updatedList = _flightIDList.value.toMutableList().apply {
                remove(flightID)
            }
            dataStorePreferences.saveFlightIDList(updatedList)
            _flightIDList.value = updatedList
        }
    }
}
