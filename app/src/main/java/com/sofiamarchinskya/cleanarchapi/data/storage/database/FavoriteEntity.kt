package com.sofiamarchinskya.cleanarchapi.data.storage.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.sofiamarchinskya.cleanarchapi.domain.model.DomainPersonModel

@Entity(tableName = "favorite")
data class FavoriteEntity(
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
) {
    fun toDomainPersonModel() = DomainPersonModel(
        name = name,
        height = height,
        url = url,
        mass = mass,
        hair_color = hair_color,
        skin_color = skin_color,
        eye_color = eye_color,
        birth_year = birth_year,
        gender = gender,
        homeworld = homeworld,
        isFavourite = true
    )
}