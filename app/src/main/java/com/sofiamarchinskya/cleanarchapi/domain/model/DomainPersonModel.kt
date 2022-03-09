package com.sofiamarchinskya.cleanarchapi.domain.model

import android.os.Parcelable
import com.sofiamarchinskya.cleanarchapi.data.storage.database.FavoriteEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class DomainPersonModel(
    val name: String,
    val height: Int,
    val isFavourite: Boolean,
    val url: String,
    val mass: Int,
    val hair_color: String,
    val skin_color: String,
    val eye_color: String,
    val birth_year: String,
    val gender: String,
    val homeworld: String,
    //@SerializedName("films") val films : List<String>,
    //@SerializedName("species") val species : List<String>,
    //@SerializedName("vehicles") val vehicles : List<String>,
    //@SerializedName("starships") val starships : List<String>,
) : Parcelable {
    fun toFavoriteEntity() = FavoriteEntity(
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
    )
}