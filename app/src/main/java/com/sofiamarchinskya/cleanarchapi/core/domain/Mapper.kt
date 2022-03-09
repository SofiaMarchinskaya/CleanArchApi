package com.sofiamarchinskya.cleanarchapi.core.domain

import com.sofiamarchinskya.cleanarchapi.data.DataPerson
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
            url = input.networkProduct.url
        )
}
