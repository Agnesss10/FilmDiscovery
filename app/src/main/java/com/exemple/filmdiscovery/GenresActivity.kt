package com.exemple.filmdiscovery

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class GenresActivity : AppCompatActivity() {
    private lateinit var action: Button
    private lateinit var comedie: Button
    private lateinit var fantastique: Button
    private lateinit var aventure: Button
    private lateinit var thriller: Button
    private lateinit var horreur: Button
    private lateinit var crime: Button
    private lateinit var drame: Button
    private lateinit var musique: Button
    private lateinit var animation: Button
    private lateinit var romance: Button
    private lateinit var western: Button
    private lateinit var documentaire: Button
    private lateinit var sf: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_genres)
        action = findViewById(R.id.action)
        action.setOnClickListener {
            val genreId = 28
            val rand = Random()
            val i = rand.nextInt(1000)
            getRetrofitResponseByID(genreId,i)
            Log.v("Tag", "valeur de i = $i genreID = $genreId")
        }
        comedie = findViewById(R.id.comedie)
        comedie.setOnClickListener {
            val genreId = 35
            val rand = Random()
            val i = rand.nextInt(1000)
            getRetrofitResponseByID(genreId,i)
            Log.v("Tag", "valeur de i = $i genreID = $genreId")
        }
        fantastique = findViewById(R.id.fantastique)
        fantastique.setOnClickListener {
            val genreId = 14
            val rand = Random()
            val i = rand.nextInt(1000)
            getRetrofitResponseByID(genreId,i)
            Log.v("Tag", "valeur de i = $i genreID = $genreId")
        }
        aventure = findViewById(R.id.aventure)
        aventure.setOnClickListener {
            val genreId = 12
            val rand = Random()
            val i = rand.nextInt(1000)
            getRetrofitResponseByID(genreId,i)
            Log.v("Tag", "valeur de i = $i genreID = $genreId")
        }
        thriller = findViewById(R.id.thriller)
        thriller.setOnClickListener {
            val genreId = 53
            val rand = Random()
            val i = rand.nextInt(1000)
            getRetrofitResponseByID(genreId,i)
            Log.v("Tag", "valeur de i = $i genreID = $genreId")
        }
        horreur = findViewById(R.id.horreur)
        horreur.setOnClickListener {
            val genreId = 27
            val rand = Random()
            val i = rand.nextInt(1000)
            getRetrofitResponseByID(genreId,i)
            Log.v("Tag", "valeur de i = $i genreID = $genreId")
        }
        crime = findViewById(R.id.crime)
        crime.setOnClickListener {
            val genreId = 80
            val rand = Random()
            val i = rand.nextInt(1000)
            getRetrofitResponseByID(genreId,i)
            Log.v("Tag", "valeur de i = $i genreID = $genreId")
        }
        drame = findViewById(R.id.drame)
        drame.setOnClickListener {
            val genreId = 18
            val rand = Random()
            val i = rand.nextInt(1000)
            getRetrofitResponseByID(genreId,i)
            Log.v("Tag", "valeur de i = $i genreID = $genreId")
        }
        musique = findViewById(R.id.musique)
        musique.setOnClickListener {
            val genreId = 10402
            val rand = Random()
            val i = rand.nextInt(1000)
            getRetrofitResponseByID(genreId,i)
            Log.v("Tag", "valeur de i = $i genreID = $genreId")
        }
        animation = findViewById(R.id.animation)
        animation.setOnClickListener {
            val genreId = 16
            val rand = Random()
            val i = rand.nextInt(1000)
            getRetrofitResponseByID(genreId,i)
            Log.v("Tag", "valeur de i = $i genreID = $genreId")
        }
        romance = findViewById(R.id.romance)
        romance.setOnClickListener {
            val genreId = 10749
            val rand = Random()
            val i = rand.nextInt(1000)
            getRetrofitResponseByID(genreId,i)
            Log.v("Tag", "valeur de i = $i genreID = $genreId")
        }
        western = findViewById(R.id.western)
        western.setOnClickListener {
            val genreId = 37
            val rand = Random()
            val i = rand.nextInt(1000)
            getRetrofitResponseByID(genreId,i)
            Log.v("Tag", "valeur de i = $i genreID = $genreId")
        }
        documentaire = findViewById(R.id.documentaire)
        documentaire.setOnClickListener {
            val genreId = 99
            val rand = Random()
            val i = rand.nextInt(1000)
            getRetrofitResponseByID(genreId,i)
            Log.v("Tag", "valeur de i = $i genreID = $genreId")
        }
        sf = findViewById(R.id.sf)
        sf.setOnClickListener {
            val genreId = 878
            val rand = Random()
            val i = rand.nextInt(1000)
            getRetrofitResponseByID(genreId,i)
            Log.v("Tag", "valeur de i = $i genreID = $genreId")
        }
    }

    private fun getRetrofitResponseByID(genreId: Int, id: Int) {
        val filmApi = Servicey.getFilmApi()
        val responseCall = filmApi.getFilm(Credentials.API_KEY, genreId, id)
        responseCall.enqueue(object : Callback<Films> {
            override fun onResponse(call: Call<Films>, response: Response<Films>) {
                if (response.code() == 200) {
                    val movies = response.body()?.getResults()
                    if (movies != null) {
                        val rand = Random()
                        val movie = movies[rand.nextInt(movies.size)]
                        Log.v("Tag", "The Response " + movie.getTitle())
                        Log.v("Tag", "The Response " + movie.getPosterPath())
                        Log.v("Tag", "The Response $movie")

                        val i = Intent(baseContext, FilmsActivity::class.java)
                        i.putExtra("movie", movie)
                        i.putExtra("genreID", genreId)
                        i.putExtra("title", movie.getTitle())
                        i.putExtra("posterPath", movie.getPosterPath())
                        startActivity(i)
                    } else {
                        // Aucun film trouvé pour le genre spécifié
                        Toast.makeText(
                            applicationContext,
                            "Aucun film trouvé pour le genre spécifié",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
            override fun onFailure(call: Call<Films>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}