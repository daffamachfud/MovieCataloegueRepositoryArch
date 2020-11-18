package com.onoh.moviecataloeguerepositoryarch.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="trendentities")
data class TrendEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name="trendId")
    var trendId:Int?,

    @ColumnInfo(name="title")
    var title:String?,

    @ColumnInfo(name="originalName")
    var originalName:String?,

    @ColumnInfo(name="rating")
    var rating:Double?,

    @ColumnInfo(name="overview")
    var overview:String?,

    @ColumnInfo(name="dateRelease")
    var dateRelease:String?,

    @ColumnInfo(name="posterImage")
    var posterImage:String?,

    @ColumnInfo(name="mediaType")
    var mediaType:String?,

    @ColumnInfo(name="favorite")
    var favorite:Boolean?
)