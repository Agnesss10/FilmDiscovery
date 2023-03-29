package com.exemple.filmdiscovery


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


class MainActivity : AppCompatActivity() {
    lateinit var btn: Button

    // Interface Accueil
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Bouton qui genère un film aléatoirement
        btn = findViewById(R.id.activity_films)

        btn.setOnClickListener {
            val rand = Random()
            val i = rand.nextInt(1000)
            getRetrofitResponseByID(i)
            Log.v("Tag", "valeur de i = $i")
        }
    }
    // Récupérer le film a partir de son ID
    private fun getRetrofitResponseByID(id: Int) {
        val filmApi = Servicey.getFilmApi()
        val responseCall = filmApi.getFilm(id, Credentials.API_KEY)
        responseCall.enqueue(object : Callback<ModelFilm> {
            override fun onResponse(call: Call<ModelFilm>, response: Response<ModelFilm>) {
                if (response.code() == 200) {
                    val movie = response.body()
                    Log.v("Tag", "The Response " + movie?.getTitle())
                    Log.v("Tag", "The Response " + movie?.getOriginalLanguage())
                    Log.v("Tag", "The Response " + movie?.getRuntime())
                    Log.v("Tag", "The Response " + movie?.getPosterPath())
                    Log.v("Tag", "The Response $movie")

                    val i = Intent(baseContext, FilmsActivity::class.java)
                    i.putExtra("movie", movie)
                    i.putExtra("langue", movie?.getOriginalLanguage())
                    i.putExtra("time", movie?.getRuntime())
                    startActivity(i)
                }
            }
            // Traitement des erreurs
            override fun onFailure(call: Call<ModelFilm>, t: Throwable) {
                when (t) {
                    is IOException -> {
                        // Erreur de connexion Internet
                        Toast.makeText(applicationContext, "Veuillez vérifier votre connexion Internet", Toast.LENGTH_LONG).show()
                    }
                    else -> {
                        // Autres erreurs
                        Toast.makeText(applicationContext, "Une erreur est survenue lors de l'appel à l'API", Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
    }

}
