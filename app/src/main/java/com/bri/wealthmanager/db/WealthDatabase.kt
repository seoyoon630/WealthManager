package com.bri.wealthmanager.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.bri.wealthmanager.db.dao.AssetDao
import com.bri.wealthmanager.db.dao.CategoryDao
import com.bri.wealthmanager.db.data.AssetData
import com.bri.wealthmanager.db.data.CategoryData

@Database(entities = [AssetData::class, CategoryData::class], version = 1)
abstract class WealthDatabase : RoomDatabase() {

    abstract fun assetDao(): AssetDao
    abstract fun categoryDao(): CategoryDao

    companion object {
        private const val DATABASE_NAME = "wealth.db"
        private lateinit var sInstance: WealthDatabase

        fun getInstance(context: Context): WealthDatabase {
            if (!::sInstance.isInitialized) {
                synchronized(WealthDatabase::class.java) {
                    if (!::sInstance.isInitialized) {
                        sInstance = Room.databaseBuilder(context, WealthDatabase::class.java, DATABASE_NAME)
                                .createFromAsset("wealth.db")
                                .build()
                    }
                }
            }
            return sInstance
        }
    }
}

