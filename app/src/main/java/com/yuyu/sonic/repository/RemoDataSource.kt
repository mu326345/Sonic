package com.yuyu.sonic.repository

import com.yuyu.sonic.data.FlightDataItem
import com.yuyu.sonic.network.FlightService
import retrofit2.Call

class RemoDataSource: IDataSource {

    override suspend fun getFlightData(): Call<List<FlightDataItem>> {
        return FlightService.service.getFlightData()
    }
}