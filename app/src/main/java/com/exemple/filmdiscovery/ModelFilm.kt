package com.exemple.filmdiscovery

import android.os.Parcel
import android.os.Parcelable

/*
* Model Film
* Parcelable : permet à un objet d'être transféré entre différents composants de l'app
* */
class ModelFilm() :  Parcelable{
    private var title: String? = null
    private var poster_path: String? = null
    var vote_average = 0f
    private var runtime = 0
    private var original_language: String? = null

    // Consctucteur pour initialiser les champs de la classe
    constructor(parcel: Parcel) : this() {
        title = parcel.readString()
        poster_path = parcel.readString()!!
        vote_average = parcel.readFloat()
        runtime = parcel.readInt()
        original_language = parcel.readString()!!
    }

    // Ecriture des données dans un Parcel
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(poster_path)
        parcel.writeFloat(vote_average)
        parcel.writeInt(runtime)
        parcel.writeString(original_language)
    }

    // Créer un objet de la classe ModelFilm à partir d'un Parcel
    companion object CREATOR : Parcelable.Creator<ModelFilm> {
        override fun createFromParcel(parcel: Parcel): ModelFilm {
            return ModelFilm(parcel)
        }
        // Créer un tableau de films
        override fun newArray(size: Int): Array<ModelFilm?> {
            return arrayOfNulls(size)
        }
    }

    // Getteurs
    fun getTitle(): String? {
        return title
    }

    fun getPosterPath(): String? {
        return poster_path
    }

    fun getVoteAverage(): Float {
        return vote_average
    }

    fun getRuntime(): Int {
        return runtime
    }

    fun getOriginalLanguage(): String? {
        return original_language
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "MovieModel{" +
                "title='$title'," +
                "poster_path='$poster_path'," +
                "vote_average=$vote_average," +
                "runtime='$runtime'," +
                "original_language='$original_language'" +
                '}'
    }
}