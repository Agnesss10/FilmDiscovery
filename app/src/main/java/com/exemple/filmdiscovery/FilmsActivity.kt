package com.exemple.filmdiscovery

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class FilmsActivity : AppCompatActivity() {
    private lateinit var film: Film
    private var genreID = 0
    private lateinit var home: Button
    private lateinit var details: Button
    private lateinit var genres: Button
    private lateinit var autre: Button
    private lateinit var google: Button
    private lateinit var poster: ImageView
    private lateinit var title: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Affichage de l'activity films
        setContentView(R.layout.activity_films)
        // initialisation de tout les éléments disponibles dans l'interface
        initialisation()
        // Récupération du film
        getMovie()
    }

    private fun initialisation() {
        home = findViewById(R.id.home)
        home.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        autre = findViewById(R.id.autre)
        autre.setOnClickListener {
            proposeAutre()
        }
        google = findViewById(R.id.google)
        google.setOnClickListener {
            val url = "https://www.google.com/search?q=" + film.getTitle() + " film"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
            finish()
        }
        genres = findViewById(R.id.genres)
        genres.setOnClickListener {
            val intent = Intent(this, GenresActivity::class.java)
            startActivity(intent)
        }
        details = findViewById(R.id.details)
        details.setOnClickListener{
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("movie",film)
            startActivity(intent)
        }
        poster = findViewById(R.id.poster)
        title = findViewById(R.id.title)
    }

    private fun proposeAutre() {
        val rand = Random()
        val i = rand.nextInt(1000)
        getFilmAutre(genreID,i)
        Log.v("Tag", "valeur de i = $i et genreID = $genreID")
    }

    private fun getFilmAutre(genreId: Int, id: Int) {
        val filmApi = Servicey.getFilmApi()
        val responseCall = filmApi.getFilm(Credentials.API_KEY, genreId, id)
        responseCall.enqueue(object : Callback<Films> {
            override fun onResponse(call: Call<Films>, response: Response<Films>) {
                if (response.code() == 200) {
                    val movies = response.body()?.getResults()
                    if (movies != null) {
                        val rand = Random()
                        val movie = movies[rand.nextInt(movies.size)]
                        // Récupérer un film aléatoire de la liste
                        // Utiliser le film aléatoire dans votre application
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
    private fun getMovie() {
        film = (intent.getSerializableExtra("movie") as? Film)!!
        genreID = intent.getIntExtra("genreID",0)
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500/${film.getPosterPath()}").into(poster)
        title.text = film.getTitle()
    }


}