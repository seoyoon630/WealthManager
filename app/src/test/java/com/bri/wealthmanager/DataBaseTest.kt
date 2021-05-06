@file:Suppress("NonAsciiCharacters", "TestFunctionName")

package com.bri.wealthmanager

import android.content.Context
import android.os.Build
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.bri.wealthmanager.common.convertToDisplayAmount
import com.bri.wealthmanager.db.WealthDatabase
import com.bri.wealthmanager.entity.AssetEntity
import com.bri.wealthmanager.repo.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import kotlin.random.Random

@ExperimentalCoroutinesApi
@Config(minSdk = Build.VERSION_CODES.P, maxSdk = Build.VERSION_CODES.P)
@RunWith(RobolectricTestRunner::class)
class DataBaseTest {
    private val random = Random(1)

    private lateinit var mDatabase: WealthDatabase
    private lateinit var mainDataSource: MainDataSource
    private lateinit var mainRepository: MainRepository
    private lateinit var detailDataSource: DetailDataSource
    private lateinit var detailRepository: DetailRepository

    @Before
    fun before() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        mDatabase = Room.inMemoryDatabaseBuilder(context, WealthDatabase::class.java).build()
        mainDataSource = MainDataSourceImpl(mDatabase)
        mainRepository = MainRepositoryImpl(mainDataSource)
        detailDataSource = DetailDataSourceImpl(mDatabase)
        detailRepository = DetailRepositoryImpl(detailDataSource)
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
        assert(::mainDataSource.isInitialized)
        assert(::mainRepository.isInitialized)
        assert(::detailDataSource.isInitialized)
        assert(::detailRepository.isInitialized)
        log()
    }

    private fun 목록_호출하기() {
        runBlocking {
            val list = mainRepository.getAll()
            assert(list.isNotEmpty())
            log()
            list.forEach { log(it) }
        }
    }

    private fun 데이터_추가하기() {
        runBlocking {
            val random = Random(1)
            repeat(10) {
                val amount = getRandomDouble()
                detailRepository.insert("자산$it", amount)
            }
            log()
        }
    }

    private fun 데이터_불러오기() {
        runBlocking {
            val data0 = detailRepository.get(-1)
            println(data0)
            assert(data0 == null)

            val data1 = detailRepository.get(1)
            println(data1)
            assert(data1 != null)
        }
    }

    private fun 데이터_변경하기() {
        runBlocking {
            detailRepository.get(1)?.let { data ->
                val amount = getRandomDouble()
                detailRepository.update(data.id, "변경자산", amount)
                log()
            }
        }
    }

    private fun 차트데이터_변환() {
        runBlocking {
            val data = mainRepository.getAll()
            data.forEach {
                if (it is AssetEntity) {
                    println("${it.amount.convertToDisplayAmount()} (${it.ratio})")
                }
            }
        }
    }

    private fun getRandomDouble(until: Int = 10000): Double {
        return random.nextInt(0, until).toDouble()
    }

    private fun getRandomInt(until: Int): Int {
        return random.nextInt(0, until)
    }
}

