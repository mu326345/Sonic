package com.yuyu.sonic.home

import android.util.Log
import androidx.lifecycle.*
import com.yuyu.sonic.data.FlightDataItem
import com.yuyu.sonic.data.OrderState
import com.yuyu.sonic.repository.IRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(val repository: IRepository) : ViewModel() {

    private val _checkState = MutableLiveData<OrderState>()
    val checkState: LiveData<OrderState>
        get() = _checkState

    var newest: List<FlightDataItem>? = null
    var oldest: List<FlightDataItem>? = null

    init {
        getFlightData()
    }

    private fun getFlightData() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getFlightData {
                newest = it.sortedByDescending { it.flight_number }
                oldest = it
                _checkState.value = OrderState.OLDEST
            }
        }
    }

    fun updateState(state: OrderState) {
        _checkState.value = state
    }
}