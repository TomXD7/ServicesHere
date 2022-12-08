package com.example.serviceshere

import android.app.Application
import androidx.room.Room
import com.example.serviceshere.DataBase.ServiceHereDatabase

class ServiceHereApplication: Application() {
    companion object {
        lateinit var database: ServiceHereDatabase
    }
    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this,
            ServiceHereDatabase::class.java,
            "ServiceHereDB")
            //.fallbackToDestructiveMigration()
            .build()


    }
}