@file:Suppress("NonAsciiCharacters", "TestFunctionName")

package com.bri.wealthmanager

import android.content.Context
import android.os.Build
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.bri.wealthmanager.common.convertToDisplayAmount
import com.bri.wealthmanager.db.WealthDatabase
import com.bri.wealthmanager.db.dao.AssetDao
import com.bri.wealthmanager.db.data.AssetData
import com.bri.wealthmanager.entity.convertToEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import kotlin.math.absoluteValue
import kotlin.random.Random

@ExperimentalCoroutinesApi
@Config(minSdk = Build.VERSION_CODES.P, maxSdk = Build.VERSION_CODES.P)
@RunWith(RobolectricTestRunner::class)
class DataBaseTest {
    private lateinit var mDatabase: WealthDatabase
    private lateinit var assetDao: AssetDao

    @Before
    fun before() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        mDatabase = Room.inMemoryDatabaseBuilder(context, WealthDatabase::class.java)
            .build()
        assetDao = mDatabase.assetDao()
    }

    @Test
    fun main() {
        데이터베이스_초기화()
        데이터_추가하기()
        목록_호출하기()
        println("======================================")
        데이터_변경하기()
        목록_호출하기()
        println("======================================")
        데이터_불러오기()
        println("======================================")
        차트데이터_변환()
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
            val random = Random(1)
            repeat(10) {
                val amount = random.nextInt(1, 10000000).absoluteValue.toDouble()
                val data = AssetData("자산$it", amount, amount.convertToDisplayAmount())
                assetDao.insert(data)
            }
            log()
        }
    }

    private fun 데이터_불러오기() {
        runBlocking {
            val data0 = assetDao.get(-1)
            println(data0)
            assert(data0 == null)

            val data1 = assetDao.get(1)
            println(data1)
            assert(data1 != null)
        }
    }

    private fun 데이터_변경하기() {
        runBlocking {
            val data = assetDao.getAll()[3]
            val update = AssetData("변경자산", data.amount, data.displayAmount, data.id)
            assetDao.update(update)
        }
    }

    private fun 차트데이터_변환() {
        runBlocking {
            val data = assetDao.getAll()
            val totalAmount = data.map { it.amount }.sum()
            data.map { it.convertToEntity(totalAmount) }.forEach {
                println("${it.amount} / $totalAmount -> ${it.pieData.dataSet.getEntryForIndex(0).value}")
            }
        }
    }
}

