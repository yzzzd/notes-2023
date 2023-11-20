package com.yazid.notes.di

import android.content.Context
import com.yazid.notes.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) = AppDatabase.getInstance(context)

    @Singleton
    @Provides
    fun provideNoteDao(database: AppDatabase) = database.noteDao()
}