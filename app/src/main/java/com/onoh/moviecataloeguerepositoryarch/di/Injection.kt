package com.onoh.moviecataloeguerepositoryarch.di

import android.content.Context
import com.onoh.moviecataloeguerepositoryarch.data.local.LocalDataSource
import com.onoh.moviecataloeguerepositoryarch.data.local.room.AppDatabase
import com.onoh.moviecataloeguerepositoryarch.data.remote.RemoteDataSource
import com.onoh.moviecataloeguerepositoryarch.utils.AppExecutors
import com.onoh.moviecataloeguerepositoryarch.data.AppRepository

object Injection {
    fun provideRepository(context: Context): AppRepository {

        val database = AppDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.appDao())
        val appExecutors = AppExecutors()

        return AppRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}