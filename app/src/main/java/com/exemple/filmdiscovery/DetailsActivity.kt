package com.exemple.filmdiscovery

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import java.text.NumberFormat

class DetailsActivity : AppCompatActivity() {
    private lateinit var film: Film
    private var genreID = 0
    private lateinit var home: Button
    private lateinit var genres: Button
    private lateinit var google: Button
    private lateinit var poster: ImageView
    private lateinit var title: TextView
    private lateinit var langue: TextView
    private lateinit var overview: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        init()
        getDetails()
    }

    private fun init(){
        home = findViewById(R.id.home)
        home.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
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
        poster = findViewById(R.id.poster)
        title = findViewById(R.id.title)
        langue = findViewById(R.id.langue)
        overview = findViewById(R.id.overview)
    }

    private fun getDetails() {
        film = (intent.getSerializableExtra("movie") as? Film)!!
        genreID = intent.getIntExtra("genreID",0)
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500/${film.getPosterPath()}").into(poster)
        title.text = film.getTitle()
        langue.text = film.getOriginalLanguage()?.uppercase()
        overview.text = film.getOverview()
    }
}