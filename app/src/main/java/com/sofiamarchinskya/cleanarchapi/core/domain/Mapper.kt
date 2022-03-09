package com.sofiamarchinskya.cleanarchapi.core.domain

import com.sofiamarchinskya.cleanarchapi.data.DataPerson
import com.sofiamarchinskya.cleanarchapi.data.net.PersonServerModel
import com.sofiamarchinskya.cleanarchapi.domain.model.DomainPersonModel

interface Mapper<I, O> {
    fun map(input: I): O
}

class MapperImpl : Mapper<DataPerson, DomainPersonModel> {
    override fun map(input: DataPerson): DomainPersonModel =
        DomainPersonModel(
            name = input.networkProduct.name,
            height = input.networkProduct.height,
            isFavourite = input.isFavourite,
            url = input.networkProduct.url,
            mass = input.networkProduct.mass,
            hair_color = input.networkProduct.hair_color,
            eye_color = input.networkProduct.hair_color,
            birth_year = input.networkProduct.birth_year,
            gender = input.networkProduct.gender,
            homeworld = input.networkProduct.homeworld,
            skin_color = input.networkProduct.skin_color
        )
}
