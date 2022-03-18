package com.sofiamarchinskya.cleanarchapi.data

import com.sofiamarchinskya.cleanarchapi.data.net.PersonServerModel
import com.sofiamarchinskya.cleanarchapi.domain.StarWarsRepository
import com.sofiamarchinskya.cleanarchapi.domain.model.DomainPersonModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRepository() : StarWarsRepository {
    private var peopleData: LinkedHashMap<String, DomainPersonModel> = LinkedHashMap()
    private val remoteList = listOf(
        PersonServerModel(
            name = "name1",
            height = 111,
            url = "url1",
            mass = 222,
            hair_color = "hair_color1",
            skin_color = "skin_color1",
            eye_color = "eye_color1",
            birth_year = "birth_year1",
            gender = "gender1",
            homeworld = "homeworld1"
        ), PersonServerModel(
            name = "name2",
            height = 112,
            url = "url2",
            mass = 223,
            hair_color = "hair_color2",
            skin_color = "skin_color2",
            eye_color = "eye_color2",
            birth_year = "birth_year2",
            gender = "gender2",
            homeworld = "homeworld2"
        ),
        PersonServerModel(
            name = "name3",
            height = 113,
            url = "url3",
            mass = 222,
            hair_color = "hair_color3",
            skin_color = "skin_color3",
            eye_color = "eye_color1",
            birth_year = "birth_year3",
            gender = "gender1",
            homeworld = "homewor3d1"
        )
    )

    override suspend fun getPersonList(): List<DomainPersonModel> = mapProducts(remoteList)

    override fun getFavoritesList(): Flow<List<DomainPersonModel>> = flow {
        emit(peopleData.values.toList())
    }

    override suspend fun addPersonToFavorite(personModel: DomainPersonModel) {
        peopleData[personModel.url] = personModel
    }

    override suspend fun delete(url: String) {
        peopleData.remove(url)
    }
    private fun mapProducts(personServerList: List<PersonServerModel>): List<DomainPersonModel> {
        return personServerList.map {
            DataPerson(it, peopleData.contains(it.url)).toDomainPersonModel()
        }
    }
}