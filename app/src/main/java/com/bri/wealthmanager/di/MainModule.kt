package com.bri.wealthmanager.di

import com.bri.wealthmanager.db.WealthDatabase
import com.bri.wealthmanager.repo.MainDataSource
import com.bri.wealthmanager.repo.MainDataSourceImpl
import com.bri.wealthmanager.repo.MainRepository
import com.bri.wealthmanager.repo.MainRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class MainModule {

    @Provides
    fun provideMainDataSource(database: WealthDatabase) : MainDataSource = MainDataSourceImpl(database)

    @Provides
    fun provideMainRepository(dataSource: MainDataSource) : MainRepository = MainRepositoryImpl(dataSource)
}