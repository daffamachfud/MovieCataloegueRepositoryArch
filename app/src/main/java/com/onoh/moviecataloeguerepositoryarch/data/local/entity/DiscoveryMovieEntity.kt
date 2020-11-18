package com.onoh.moviecataloeguerepositoryarch.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="dsvmoviesentities")
data class DiscoveryMovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name="movieId")
    var movieId:Int?,

    @ColumnInfo(name="title")
    var title:String?,

    @ColumnInfo(name="rating")
    var rating:Double?,

    @ColumnInfo(name="overview")
    var overview:String?,

    @ColumnInfo(name="dateRelease")
    var dateRelease:String?,

    @ColumnInfo(name="posterImage")
    var posterImage:String?,

    @ColumnInfo(name="favorite")
    var favorite:Boolean?
)