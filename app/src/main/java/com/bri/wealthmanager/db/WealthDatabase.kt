package com.bri.wealthmanager.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bri.wealthmanager.db.dao.AssetDao
import com.bri.wealthmanager.db.entity.AssetEntity

@Database(entities = [AssetEntity::class], version = 1)
abstract class WealthDatabase : RoomDatabase() {

    abstract fun assetDao(): AssetDao

    companion object {
        const val DATABASE_NAME = "WEALTH"
        private lateinit var sInstance: WealthDatabase

        fun getInstance(context: Context): WealthDatabase {
            if (!::sInstance.isInitialized) {
                synchronized(WealthDatabase::class.java) {
                    if (!::sInstance.isInitialized) {
                        sInstance = Room.databaseBuilder(context, WealthDatabase::class.java, DATABASE_NAME).build()
                    }
                }
            }
            return sInstance
        }
    }
}
