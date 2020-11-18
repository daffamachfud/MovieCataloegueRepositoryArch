package com.onoh.moviecataloeguerepositoryarch.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tvshowentities")
data class TvShowEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name="tvShowId")
    var tvShowId:Int?,

    @ColumnInfo(name="title")
    var title:String?,

    @ColumnInfo(name="rating")
    var rating: Double?,

    @ColumnInfo(name="overview")
    var overview:String?,

    @ColumnInfo(name="episodes")
    var episodes:Int?,

    @ColumnInfo(name="season")
    var season:Int?,

    @ColumnInfo(name="posterImage")
    var posterImage:String?,

    @ColumnInfo(name="backdropImage")
    var backdropImage:String?,

    @ColumnInfo(name="favorite")
    var favorite:Boolean = false
)