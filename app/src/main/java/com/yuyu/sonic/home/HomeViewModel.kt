package com.yuyu.sonic.home

import android.util.Log
import androidx.lifecycle.*
import com.yuyu.sonic.data.FlightDataItem
import com.yuyu.sonic.repository.IRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(val repository: IRepository): ViewModel() {

    private val _flight = MutableLiveData<List<FlightDataItem>>()
    val flight: LiveData<List<FlightDataItem>>
        get() = _flight

    val flightItem = Transformations.map(flight) {
//        it.data
        it
    }

    init {
        getFlightData()
    }

    private fun getFlightData() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getFlightData {
                Log.v("QAQ", "$it")
                _flight.value = it
            }
        }
    }
}