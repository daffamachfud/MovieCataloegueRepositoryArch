package com.onoh.moviecataloeguerepositoryarch.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.onoh.moviecataloeguerepositoryarch.data.local.entity.*

@Database(entities = [MovieEntity::class, TvShowEntity::class, DiscoveryMovieEntity::class,
                      DiscoveryTvEntity::class,TrendEntity::class,CastMovieEntity::class,CastTvEntity::class],
                      version = 1,exportSchema = false)

abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao() : AppDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(context.applicationContext,
                    AppDatabase::class.java,
                    "App.db").build()
            }
    }


}