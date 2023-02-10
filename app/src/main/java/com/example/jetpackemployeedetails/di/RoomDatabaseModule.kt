package com.example.jetpackemployeedetails.di

import android.content.Context
import androidx.room.Room
import com.example.jetpackemployeedetails.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomDatabaseModule {

    @Singleton
    @Provides
    fun provideYourDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        AppDatabase::class.java,
        "Employee.db"
    ).build()

    @Singleton
    @Provides
    fun provideUserDetailsDao(db: AppDatabase) = db.appDao()
}