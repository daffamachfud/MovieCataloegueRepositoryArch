package com.onoh.moviecataloeguerepositoryarch.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="dsvtvsentities")
data class DiscoveryTvEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name="tvId")
    var tvId:Int?,

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