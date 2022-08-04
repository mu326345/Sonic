package com.yuyu.sonic.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yuyu.sonic.data.Core
import com.yuyu.sonic.data.FlightDataItem

class DetailViewModel: ViewModel() {

    private val _flightSelect = MutableLiveData<FlightDataItem>()
    val flightSelect: LiveData<FlightDataItem>
        get() = _flightSelect

    var coreData: Core? = null


    fun getBundleData(data: FlightDataItem) {
        coreData = data.rocket?.first_stage?.cores?.get(0)

        _flightSelect.value = data
    }
}
