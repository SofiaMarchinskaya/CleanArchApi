package com.sofiamarchinskya.cleanarchapi.presentation.model

import android.os.Parcelable
import com.sofiamarchinskya.cleanarchapi.domain.model.DomainPersonModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class UIModel(
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
) : Parcelable {
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
        isFavourite = isFavourite
    )
}