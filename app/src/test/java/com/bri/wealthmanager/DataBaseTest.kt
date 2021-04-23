package com.bri.wealthmanager

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.bri.wealthmanager.db.WealthDatabase
import com.bri.wealthmanager.db.dao.AssetDao
import io.kotest.core.config.AbstractProjectConfig
import io.kotest.core.extensions.Extension
import io.kotest.core.spec.Spec
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.extensions.robolectric.RobolectricExtension
import io.kotest.extensions.robolectric.RobolectricTest
import io.kotest.matchers.shouldBe

@RobolectricTest
class DataBaseTest : BehaviorSpec() {

    private lateinit var mDatabase: WealthDatabase
    private lateinit var assetDao: AssetDao

    override fun beforeSpec(spec: Spec) {
        super.beforeSpec(spec)
        mDatabase = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), WealthDatabase::class.java)
                .allowMainThreadQueries()
                .build()
        assetDao = mDatabase.assetDao()
        println("beforeSpec")
    }

    init {
        given("데이터베이스 생성") {
            `when`("목록 가져오기") {
                and("최초 생성 시") {
                    val list = assetDao.getAll()
                    then("목록은 비어있다") { list.size shouldBe 0 }
                    println("${list.size}")
                }
            }
        }
    }
}

class ProjectConfig : AbstractProjectConfig() {
    override fun extensions(): List<Extension> = super.extensions() + RobolectricExtension()
}