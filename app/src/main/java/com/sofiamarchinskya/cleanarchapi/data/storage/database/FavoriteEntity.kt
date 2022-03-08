package com.sofiamarchinskya.cleanarchapi.data.storage.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "favorite")
data class FavoriteEntity(
    @SerializedName("name") val name: String,
    @SerializedName("height") val height: Int,
    //@SerializedName("films") val films : List<String>,
    //@SerializedName("species") val species : List<String>,
    //@SerializedName("vehicles") val vehicles : List<String>,
    //@SerializedName("starships") val starships : List<String>,
    @SerializedName("url") @PrimaryKey val url: String,
)