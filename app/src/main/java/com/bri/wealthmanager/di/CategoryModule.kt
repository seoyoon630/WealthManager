package com.bri.wealthmanager.di

import com.bri.wealthmanager.db.WealthDatabase
import com.bri.wealthmanager.repo.CategoryDataSource
import com.bri.wealthmanager.repo.CategoryDataSourceImpl
import com.bri.wealthmanager.repo.CategoryRepository
import com.bri.wealthmanager.repo.CategoryRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class CategoryModule {
    @Provides
    fun provideCategoryDataSource(database: WealthDatabase): CategoryDataSource {
        return CategoryDataSourceImpl(database)
    }

    @Provides
    fun provideCategoryRepository(dataSource: CategoryDataSource): CategoryRepository {
        return CategoryRepositoryImpl(dataSource)
    }

}