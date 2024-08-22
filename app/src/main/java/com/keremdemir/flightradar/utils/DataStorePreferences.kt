import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class DataStorePreferences(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("flightRadar")
        private val FLIGHT_ID_KEY = stringPreferencesKey("flight_id")
    }

    fun getFlightIDList(): List<String> = runBlocking {
        val preferences = context.dataStore.data.first()
        val serializedList = preferences[FLIGHT_ID_KEY] ?: ""
        if (serializedList.isNotEmpty()) {
            serializedList.split(",").map { it }
        } else {
            emptyList()
        }
    }

    suspend fun saveFlightIDList(flightList: List<String>) {
        val serializedList = flightList.joinToString(",") // Convert list to CSV format
        context.dataStore.edit { preferences -> preferences[FLIGHT_ID_KEY] = serializedList }
    }
}