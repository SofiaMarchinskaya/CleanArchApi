package com.sofiamarchinskya.cleanarchapi.domain.model

import com.sofiamarchinskya.cleanarchapi.data.storage.database.FavoriteEntity
import com.sofiamarchinskya.cleanarchapi.presentation.model.UIModel

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
){
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
    fun toUIModel() = UIModel(
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
        isFavourite = isFavourite
    )
}