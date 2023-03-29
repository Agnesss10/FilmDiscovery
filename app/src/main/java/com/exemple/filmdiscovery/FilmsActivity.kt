package com.exemple.filmdiscovery

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class FilmsActivity : AppCompatActivity() {
    private lateinit var home: ImageView
    private lateinit var autre: ImageView
    private lateinit var loupe: ImageView
    private lateinit var poster: ImageView
    private lateinit var title: TextView
    private lateinit var time: TextView
    private lateinit var note: TextView
    private lateinit var langue: TextView
    private lateinit var film: ModelFilm
    private lateinit var strTime: String
    private lateinit var strLangue: String

    // Mise en place de l'interface film
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Affichage de l'activity films
        setContentView(R.layout.activity_films)
        // initialisation de tout les éléments disponibles dans l'interface
        initialisation()
        // Récupération du film
        getMovie()
        setupMovie(film)
    }

    private fun initialisation() {
        home = findViewById(R.id.home)
        autre = findViewById(R.id.autre)
        loupe = findViewById(R.id.loupe)
        poster = findViewById(R.id.poster)
        title = findViewById(R.id.title)
        time = findViewById(R.id.time)
        note = findViewById(R.id.note)
        langue = findViewById(R.id.langue)

        // Bouton autre pour proposer un autre film
        autre.setOnClickListener {
            // Appel de la fonction
            proposeAutre()
        }

        // Bouton loupe pour rechercher le film sur google
        loupe.setOnClickListener {
            val url = "https://www.google.com/search?q=" + film.getTitle()
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
            finish()
        }

        // Bouton home pour le retour à la page d'accueil
        home.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    // Fonction qui propose un autre film
    private fun proposeAutre() {
        val rand = Random()
        val i = rand.nextInt(1000)
        getFilm(i)
        Log.v("Tag", "valeur de i = $i")
    }

    // Fonction qui récupère le film de l'API à l'aide d'un ID
    private fun getFilm(id: Int) {
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
            override fun onFailure(call: Call<ModelFilm>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    // Récupérer le film
    private fun getMovie() {
        val i = intent
        film = intent.getParcelableExtra("movie")!!
        strTime = i.getIntExtra("time", 0).toString()
        strLangue = i.getStringExtra("langue")!!
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500/${film.getPosterPath()}")
            .into(poster)
    }

    // Recupération des détails du film
    private fun setupMovie(movie: ModelFilm) {
        Log.d("movie", "setupMovie: $strTime")
        title.text = movie.getTitle()
        time.text = "$strTime min"
        note.text = movie.vote_average.toString()
        langue.text = strLangue.uppercase()
    }
}