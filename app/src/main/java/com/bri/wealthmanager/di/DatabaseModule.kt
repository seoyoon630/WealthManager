package com.bri.wealthmanager.di

import android.content.Context
import com.bri.wealthmanager.db.WealthDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideWealthDataBase(@ApplicationContext context: Context): WealthDatabase =
        WealthDatabase.getInstance(context)
}