package com.bri.wealthmanager.di

import com.bri.wealthmanager.db.WealthDatabase
import com.bri.wealthmanager.repo.DetailDataSource
import com.bri.wealthmanager.repo.DetailDataSourceImpl
import com.bri.wealthmanager.repo.DetailRepository
import com.bri.wealthmanager.repo.DetailRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DetailModule {
    @Provides
    fun provideDetailDataSource(database: WealthDatabase) : DetailDataSource = DetailDataSourceImpl(database)

    @Provides
    fun provideDetailRepository(dataSource: DetailDataSource) : DetailRepository = DetailRepositoryImpl(dataSource)
}