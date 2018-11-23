package com.example.tanyongrui.intermediatemovie

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_view_movie.*

class ViewMovieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_movie)

        /*
        val p = MovieEntity()
        //p.title = "title of the movie...."
        titleMovieText.text = p.title
        overviewText.text = p.overview
        languageText.text = p.language
        releaseDateText.text = p.releaseDate
        if (p.suitableAge == true) {
            suitableAgeText.text = "Yes"
        }
        else{
            suitableAgeText.text = "No"
        }
        */
        val receivedMovieObj = intent.getSerializableExtra("callThisShit") as MovieEntity
        //Log.e("movie entity object " + receivedMovieObj.title)
        titleMovieText.text = receivedMovieObj.title
        overviewText.text = receivedMovieObj.overview
        languageText.text = receivedMovieObj.language
        releaseDateText.text = receivedMovieObj.releaseDate

        Log.e("---------below is movie entity ----- ", "------------")
        Log.e("",receivedMovieObj.toString())
        Log.e("title of movie", titleMovieText.text.toString())
        Log.e("overview of movie ", overviewText.text.toString())
        Log.e("language used ", languageText.text.toString())
        Log.e("release date", releaseDate.text.toString())
        Log.e("suitable age", suitableAgeText.text.toString())

    }
}
