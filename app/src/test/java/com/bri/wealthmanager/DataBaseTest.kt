@file:Suppress("NonAsciiCharacters", "TestFunctionName")

package com.bri.wealthmanager

import android.os.Build
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.bri.wealthmanager.db.WealthDatabase
import com.bri.wealthmanager.db.dao.AssetDao
import com.bri.wealthmanager.db.entity.AssetEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@ExperimentalCoroutinesApi
@Config(minSdk = Build.VERSION_CODES.P, maxSdk = Build.VERSION_CODES.P)
@RunWith(RobolectricTestRunner::class)
class DataBaseTest {
    private lateinit var mDatabase: WealthDatabase
    private lateinit var assetDao: AssetDao

    @Before
    fun before() {
        mDatabase = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), WealthDatabase::class.java)
                .build()
        assetDao = mDatabase.assetDao()
    }

    @Test
    fun main() {
        데이터베이스_초기화()
        데이터_추가하기()
        목록_호출하기()
    }

    private fun 데이터베이스_초기화() {
        assert(::mDatabase.isInitialized)
        assert(::assetDao.isInitialized)
        log()
    }

    private fun 목록_호출하기() {
        runBlocking {
            val list = assetDao.getAll()
            assert(list.isNotEmpty())
            log()
            list.forEach { log(it) }
        }
    }

    private fun 데이터_추가하기() {
        runBlocking {
            val data = AssetEntity( "월급통장", 100000.toDouble())
            val data2 = AssetEntity( "월급통장", 100000.toDouble())
            assetDao.insert(data)
            assetDao.insert(data2)
            val list = assetDao.getAll()
            assert(list[0].id == 1)
            assert(list[1].id == 2)
            log()
        }
    }
}

