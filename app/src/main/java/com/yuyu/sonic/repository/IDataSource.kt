package com.yuyu.sonic.repository

import com.yuyu.sonic.data.FlightDataItem
import retrofit2.Call

interface IDataSource {

    suspend fun getFlightData(): Call<List<FlightDataItem>>
}
