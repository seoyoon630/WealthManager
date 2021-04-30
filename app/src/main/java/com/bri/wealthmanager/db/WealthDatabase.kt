package com.bri.wealthmanager.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.bri.wealthmanager.db.dao.AssetDao
import com.bri.wealthmanager.db.data.AssetData

@Database(entities = [AssetData::class], version = 2)
abstract class WealthDatabase : RoomDatabase() {

    abstract fun assetDao(): AssetDao

    companion object {
        const val DATABASE_NAME = "WEALTH"
        private lateinit var sInstance: WealthDatabase

        fun getInstance(context: Context): WealthDatabase {
            if (!::sInstance.isInitialized) {
                synchronized(WealthDatabase::class.java) {
                    if (!::sInstance.isInitialized) {
                        sInstance =
                            Room.databaseBuilder(context, WealthDatabase::class.java, DATABASE_NAME)
                                .addMigrations(MIGRATION_1_2)
                                .build()
                    }
                }
            }
            return sInstance
        }

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE WEALTH ADD COLUMN displayAmount TEXT NOT NULL default '0원'")
            }
        }
    }

}

