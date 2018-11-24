package com.example.tanyongrui.intermediatemovie

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_view_movie.*

class ViewMovieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_movie)

        registerForContextMenu(reviewText)

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
        titleMovieText.text = receivedMovieObj.title
        overviewText.text = receivedMovieObj.overview
        languageText.text = receivedMovieObj.language
        releaseDateText.text = receivedMovieObj.releaseDate

        Log.e("---------below is movie entity ----- ", "------------")
        Log.e("",receivedMovieObj.toString())
        Log.e("title of movie", titleMovieText.text.toString())
        Log.e("overview of movie ", overviewText.text.toString())
        Log.e("language used ", languageText.text.toString())
        Log.e("release date", releaseDateText.text.toString())
        Log.e("suitable age", suitableAgeText.text.toString())

    } //end of onCreate()


    //once add review text is press, function logic below
    override fun onContextItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == 1001) {
            //finish() //end the screen...
            // call intent to move to add Movie screen
            var myIntent = Intent(this, RateMovieActivity::class.java)
            startActivityForResult(myIntent, 666) // this will return the data from ratemovie
        }
        return super.onContextItemSelected(item)
    }

    //long press, the add review text will display
    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        if (v?.id == R.id.reviewText) {
            menu?.add(1,1001,1,"Add Review")
        }
    }

    //override this to get data set from rateMovie, rather than onCreate() the data will be gone/error
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var receivedRatingStarsIntent = data!!.getStringExtra("ratingStars")
        var receivedRatingDetailsIntent = data!!.getStringExtra("ratingDetails")

        ratingStarsNotVisible.visibility = View.VISIBLE
        ratingStarsNotVisible.rating = receivedRatingStarsIntent.toFloat()
        reviewText.text = receivedRatingDetailsIntent
        Log.e("rating stars " , receivedRatingStarsIntent)
        Log.e("rating details", receivedRatingDetailsIntent)
    }

}
