package com.yuyu.sonic

import android.app.Application
import com.yuyu.sonic.repository.DefaultRepository
import com.yuyu.sonic.repository.RemoDataSource

class Application: Application() {

    lateinit var repository: DefaultRepository

    override fun onCreate() {
        super.onCreate()
        repository = DefaultRepository(RemoDataSource())
    }
}