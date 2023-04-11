package com.exemple.filmdiscovery

import java.io.Serializable
import java.util.*

class Films : Serializable{
    private lateinit var results: Array<Film>
    fun getResults(): Array<Film> {
        return results
    }
}
class Film : Serializable {
    private var title: String? = null
    private var poster_path: String? = null
    private var original_language: String? = null
    private var overview: String? = null

    fun getTitle(): String? {
        return title
    }

    fun getPosterPath(): String? {
        return poster_path
    }

    fun getOriginalLanguage(): String? {
        return original_language
    }

    fun getOverview(): String? {
        return overview
    }
}