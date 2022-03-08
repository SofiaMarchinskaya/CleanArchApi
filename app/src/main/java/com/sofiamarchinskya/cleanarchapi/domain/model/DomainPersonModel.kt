package com.sofiamarchinskya.cleanarchapi.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DomainPersonModel(
    val name: String,
    val height: Int,
    val isFavourite: Boolean,
    val url: String
//    @SerializedName("mass") val mass: Int,
//    @SerializedName("hair_color") val hair_color: String,
//    @SerializedName("skin_color") val skin_color: String,
//    @SerializedName("eye_color") val eye_color: String,
//    @SerializedName("birth_year") val birth_year: String,
//    @SerializedName("gender") val gender: String,
//    @SerializedName("homeworld") val homeworld: String,
//    //@SerializedName("films") val films : List<String>,
//    //@SerializedName("species") val species : List<String>,
//    //@SerializedName("vehicles") val vehicles : List<String>,
//    //@SerializedName("starships") val starships : List<String>,
//    @SerializedName("created") val created: String,
//    @SerializedName("edited") val edited: String,
):Parcelable