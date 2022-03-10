package com.sofiamarchinskya.cleanarchapi.data

import com.sofiamarchinskya.cleanarchapi.data.net.PersonServerModel
import com.sofiamarchinskya.cleanarchapi.domain.model.DomainPersonModel

data class DataPerson(
    val networkProduct: PersonServerModel,
    val isFavourite: Boolean
) {
    fun toDomainPersonModel() =
        DomainPersonModel(
            name = networkProduct.name,
            height = networkProduct.height,
            isFavourite = isFavourite,
            url = networkProduct.url,
            mass = networkProduct.mass,
            hair_color = networkProduct.hair_color,
            eye_color = networkProduct.hair_color,
            birth_year = networkProduct.birth_year,
            gender = networkProduct.gender,
            homeworld = networkProduct.homeworld,
            skin_color = networkProduct.skin_color
        )
}