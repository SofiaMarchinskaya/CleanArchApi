package com.sofiamarchinskya.cleanarchapi.data

import com.sofiamarchinskya.cleanarchapi.core.domain.Mapper
import com.sofiamarchinskya.cleanarchapi.data.net.PersonServerModel
import com.sofiamarchinskya.cleanarchapi.data.net.StarWarsService
import com.sofiamarchinskya.cleanarchapi.data.storage.PersonStorage
import com.sofiamarchinskya.cleanarchapi.data.storage.database.FavoriteEntity
import com.sofiamarchinskya.cleanarchapi.domain.StarWarsRepository
import com.sofiamarchinskya.cleanarchapi.domain.model.DomainPersonModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StarWarsRepositoryImpl @Inject constructor(
    private val starWarsService: StarWarsService,
    private val productDataMapper: Mapper<DataPerson, DomainPersonModel>,
    private val storage: PersonStorage
) : StarWarsRepository {

    override suspend fun getPersonList(): List<DomainPersonModel> =
        mapProducts(starWarsService.getPersonList())

    override fun getFavoritesList(): Flow<List<DomainPersonModel>> =
        storage.getAllFavorite()

    override suspend fun addPersonToFavorite(personModel: DomainPersonModel) {
        storage.insert(
            FavoriteEntity(
                url = personModel.url,
                name = personModel.name,
                height = personModel.height
            )
        )
    }

    override suspend fun delete(url: String) {
        storage.delete(url)
    }

    private suspend fun mapProducts(personServerList: List<PersonServerModel>): List<DomainPersonModel> {
        return personServerList.map {
            productDataMapper.map(DataPerson(it, storage.isFavorite(it.url)))
        }
    }
}

data class DataPerson(
    val networkProduct: PersonServerModel,
    val isFavourite: Boolean
)