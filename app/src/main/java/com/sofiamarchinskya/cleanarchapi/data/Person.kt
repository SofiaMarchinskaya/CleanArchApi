package com.sofiamarchinskya.cleanarchapi.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "favorite")
data class Person(
    @SerializedName("name") val name: String,
    @SerializedName("height") val height: Int,
    @SerializedName("mass") val mass: Int,
    @SerializedName("hair_color") val hair_color: String,
    @SerializedName("skin_color") val skin_color: String,
    @SerializedName("eye_color") val eye_color: String,
    @SerializedName("birth_year") val birth_year: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("homeworld") val homeworld: String,
    @SerializedName("url") @PrimaryKey val url: String,
    @SerializedName("isfavorite") var isfavorite:Boolean = false
)