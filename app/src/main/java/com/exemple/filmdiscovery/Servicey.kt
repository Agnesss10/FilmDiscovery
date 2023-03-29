package com.exemple.filmdiscovery

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Implémentation de Retrofit
// Permet d'implémenter des appels à des webservices

object Servicey {
    private val retrofitBuilder: Retrofit.Builder = Retrofit.Builder()
        .baseUrl(Credentials.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())

    private val retrofit: Retrofit = retrofitBuilder.build()

    private val movieApi: ApiFilm = retrofit.create(ApiFilm::class.java)

    fun getFilmApi(): ApiFilm {
        return movieApi
    }
}