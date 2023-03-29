package com.exemple.filmdiscovery

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

// Interface de l'API
interface ApiFilm {
    // Spécifier l'URL finale de l'API
    @GET("3/movie/{movie_id}?")
    // Récupération du film
    fun getFilm(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String
    ): Call<ModelFilm>
}
