package com.onoh.moviecataloeguerepositoryarch.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName="casttventities")
data class CastTvEntity(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name="cast_id")
    var castId:Int?,

    @ColumnInfo(name="character")
    var character:String?,

    @ColumnInfo(name="gender")
    var gender:Int?,

    @ColumnInfo(name="credit_id")
    var creditId:String?,

    @ColumnInfo(name="name")
    var name:String?,

    @ColumnInfo(name="profile_path")
    var profilePath:String?,

    @ColumnInfo(name="id")
    var id:Int?,

    @ColumnInfo(name="order")
    var order:Int?
)