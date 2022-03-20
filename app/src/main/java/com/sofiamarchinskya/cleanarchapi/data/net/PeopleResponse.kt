package com.sofiamarchinskya.cleanarchapi.data.net

import com.sofiamarchinskya.cleanarchapi.data.Person


data class PeopleResponse(
    val count: Int = 0,
    val next: String = "",
    val previous: String = "df",
    val results: List<Person>
)