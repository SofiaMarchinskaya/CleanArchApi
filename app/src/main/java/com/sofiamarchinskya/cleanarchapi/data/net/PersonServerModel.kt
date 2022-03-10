package com.sofiamarchinskya.cleanarchapi.data.net

import com.google.gson.annotations.SerializedName

data class PersonServerModel(
    @SerializedName("name") val name: String,
    @SerializedName("height") val height: Int,
    @SerializedName("url") val url: String,
    @SerializedName("mass") val mass: Int,
    @SerializedName("hair_color") val hair_color: String,
    @SerializedName("skin_color") val skin_color: String,
    @SerializedName("eye_color") val eye_color: String,
    @SerializedName("birth_year") val birth_year: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("homeworld") val homeworld: String,
    //@SerializedName("films") val films : List<String>,
    //@SerializedName("species") val species : List<String>,
    //@SerializedName("vehicles") val vehicles : List<String>,
    //@SerializedName("starships") val starships : List<String>,
)