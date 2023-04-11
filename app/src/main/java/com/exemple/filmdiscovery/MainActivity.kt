package com.exemple.filmdiscovery


import android.content.Intent
import android.os.Bundle

import android.widget.Button

import androidx.appcompat.app.AppCompatActivity



class MainActivity : AppCompatActivity() {
    lateinit var btn: Button

    // Interface Accueil
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn = findViewById(R.id.go)
        btn.setOnClickListener {
            val intent = Intent(this, GenresActivity::class.java)
            startActivity(intent)
        }
    }

}
