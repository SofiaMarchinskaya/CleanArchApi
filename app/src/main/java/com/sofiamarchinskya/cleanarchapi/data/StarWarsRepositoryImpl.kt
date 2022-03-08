package com.sofiamarchinskya.cleanarchapi.data

import com.sofiamarchinskya.cleanarchapi.core.domain.Mapper
import com.sofiamarchinskya.cleanarchapi.data.net.PersonServerModel
import com.sofiamarchinskya.cleanarchapi.data.net.StarWarsService
import com.sofiamarchinskya.cleanarchapi.data.storage.PersonStorage
import com.sofiamarchinskya.cleanarchapi.data.storage.database.FavoriteEntity
import com.sofiamarchinskya.cleanarchapi.domain.StarWarsRepository
import com.sofiamarchinskya.cleanarchapi.domain.model.DomainPersonModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class StarWarsRepositoryImpl@Inject constructor(
    private val starWarsService: StarWarsService,
    private val productDataMapper: Mapper<DataPerson, DomainPersonModel>,
    private val personPreferences: PersonPreferences,
    private val storage: PersonStorage

) : StarWarsRepository {

    override suspend fun getPersonList(): List<DomainPersonModel> =
    mapProducts(starWarsService.getPersonList())

    override fun getFavoritesList(): Flow<List<DomainPersonModel>> =
        storage.getAllFavorite()

    override suspend fun addPersonToFavorite(personModel:DomainPersonModel) {
        storage.insert(FavoriteEntity(url = personModel.url,name = personModel.name, height = personModel.height))
    }

    private fun mapProducts(personServerList: List<PersonServerModel>): List<DomainPersonModel> {
        return personServerList.map {
            productDataMapper.map(DataPerson(it, personPreferences.isFavourite(it.url)))
        }
    }
}

data class DataPerson(
    val networkProduct: PersonServerModel,
    val isFavourite: Boolean
)


// DataSource для SharedPreferences
interface PersonPreferences {

    fun isFavourite(id: String?): Boolean

}

class PersonPreferencesImpl : PersonPreferences {
    override fun isFavourite(id: String?): Boolean = true
}

