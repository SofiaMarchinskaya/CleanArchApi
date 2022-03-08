package com.sofiamarchinskya.cleanarchapi.core.domain

import com.sofiamarchinskya.cleanarchapi.data.DataPerson
import com.sofiamarchinskya.cleanarchapi.data.net.PersonServerModel
import com.sofiamarchinskya.cleanarchapi.domain.model.DomainPersonModel

interface Mapper<I, O> {
    fun map(input: I): O
}

interface ListMapper<I, O> : Mapper<List<I>, List<O>>

class ListMapperImpl<I, O>(
    private val mapper: Mapper<I, O>
) : ListMapper<I, O> {
    override fun map(input: List<I>): List<O> {
        return input.map { mapper.map(it) }
    }
}

class MapperImpl() : Mapper<DataPerson, DomainPersonModel> {
    override fun map(input: DataPerson): DomainPersonModel =
        DomainPersonModel(
            name = input.networkProduct.name,
            height = input.networkProduct.height,
            isFavourite = input.isFavourite,
            url = input.networkProduct.url
        )
}
