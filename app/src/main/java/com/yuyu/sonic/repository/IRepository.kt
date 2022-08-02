package com.yuyu.sonic.repository

import com.yuyu.sonic.data.FlightDataItem
import retrofit2.Call

interface IRepository {

    suspend fun getFlightData(call: (List<FlightDataItem>) -> Unit)
}