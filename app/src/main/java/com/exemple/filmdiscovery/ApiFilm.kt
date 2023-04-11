package com.exemple.filmdiscovery

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

// Interface de l'API
interface ApiFilm {
    // Spécifier l'URL finale de l'API pour récupérer les films avec un genre spécifique

    @GET("3/discover/movie?")
    fun getFilm(
        @Query("api_key") api_key: String,
        @Query("with_genres") genreId: Int,
        @Query("page") page: Int
    ): Call<Films>

}