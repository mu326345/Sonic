package com.yuyu.sonic.repository

import android.util.Log
import com.yuyu.sonic.data.FlightDataItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DefaultRepository(private val dataSource: IDataSource): IRepository {

    override suspend fun getFlightData(call: (List<FlightDataItem>) -> Unit) {
        dataSource.getFlightData().enqueue(object : Callback<List<FlightDataItem>> {
            override fun onResponse(call: Call<List<FlightDataItem>>, response: Response<List<FlightDataItem>>) {
                if(response.isSuccessful) {
                    response.body()?.let {
                        call(it)
                    }
                }
            }

            override fun onFailure(call: Call<List<FlightDataItem>>, t: Throwable) {
                Log.v("DefaultRepository", "retrofit on fail ${t.message}")
            }
        })
    }
}