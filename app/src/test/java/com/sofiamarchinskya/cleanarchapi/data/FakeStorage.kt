package com.sofiamarchinskya.cleanarchapi.data

//class FakeStorage(var data: MutableList<FavoriteEntity>? = mutableListOf()):PersonStorage {
//
//    override fun getAllFavorite(): Flow<List<DomainPersonModel>> = flow{
//       emit(ArrayList(data).map { it.toDomainPersonModel() })
//    }
//
//    override suspend fun insert(personModel: FavoriteEntity) {
//       data?.add(personModel)
//    }
//
//    override suspend fun isFavorite(url: String): Boolean  = ArrayList(data).any{it.url==url}
//
//    override suspend fun delete(url: String) {
//        data?.removeIf { it.url==url }
//    }
//}