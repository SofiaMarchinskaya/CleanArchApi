package com.sofiamarchinskya.cleanarchapi.data.net

data class PeopleResponse(
    val count: Int = 0,
    val next: String = "",
    val previous: String = "df",
    val results: List<PersonServerModel>
)