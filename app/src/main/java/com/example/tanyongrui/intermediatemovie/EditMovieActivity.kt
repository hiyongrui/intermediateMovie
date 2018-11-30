package com.example.tanyongrui.intermediatemovie

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.RadioButton
import kotlinx.android.synthetic.main.activity_add_movie.*
import kotlinx.android.synthetic.main.activity_edit_movie.*
import kotlinx.android.synthetic.main.activity_edit_movie.view.*
import kotlinx.android.synthetic.main.activity_rate_movie.*
import kotlinx.android.synthetic.main.activity_view_movie.*

class EditMovieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_movie)

        supportActionBar?.setDisplayHomeAsUpEnabled(true) //code for back button to display, or use manifest

        editCheckBoxSuitable.setOnClickListener {
            if (editCheckBoxSuitable.isChecked == true) {
                editCheckBoxViolence.setVisibility(View.VISIBLE)
                editCheckBoxLanguageUsed.setVisibility(View.VISIBLE)
            } else {
                editCheckBoxViolence.setVisibility(View.GONE)
                editCheckBoxLanguageUsed.setVisibility(View.GONE)
                editCheckBoxViolence.isChecked = false
                editCheckBoxLanguageUsed.isChecked = false
            }
        }

        val receivedEditMovieObj = intent.getSerializableExtra("editMovieObj") as MovieEntity
        editNameOfMovie.setText(receivedEditMovieObj.title)
        editDescription.setText(receivedEditMovieObj.overview)
        editReleaseDate.setText(receivedEditMovieObj.releaseDate)

        val list = mutableListOf(editRadioButtonEnglish,editRadioButtonChinese, editRadioButtonMalay, editRadioButtonTamil)

        for (i in 0..list.size-1) {
            if (list[i].text.toString() == receivedEditMovieObj.language) {
                Log.e("radio button edit found!!", "text is " + list[i].text)
                list[i].isChecked = true
            }
        }

        if (receivedEditMovieObj.suitableAge.contains("No")) {
            editCheckBoxSuitable.isChecked = true
            editCheckBoxViolence.visibility = View.VISIBLE
            editCheckBoxLanguageUsed.visibility = View.VISIBLE
            Log.e("receive edit checked true!!", "11111111111")
            if (receivedEditMovieObj.suitableAge.contains("violence, language", ignoreCase = true)) {
                Log.e("violence lanuage!!", "222222222")
                editCheckBoxViolence.isChecked = true
                editCheckBoxLanguageUsed.isChecked = true
            }
            else if ("Violence" in receivedEditMovieObj.suitableAge) {
                Log.e("violence!!", "3333333333 ")
                editCheckBoxViolence.isChecked = true
            }
            else if ("Language" in receivedEditMovieObj.suitableAge) {
                Log.e("lanuage!!", "44444444444")
                editCheckBoxLanguageUsed.isChecked = true
            }
        }

        Log.e("received edit movie object language radio button", receivedEditMovieObj.language)
        Log.e("received checkbox suitableage edits --> ", receivedEditMovieObj.suitableAge)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.save_menu, menu) //inflate the save_menu.xml from menu directory

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if ( (item?.itemId == android.R.id.home) || (item?.itemId == R.id.cancel) ) {  //code for back button to display, or use manifest
            finish() //destroy current activity, clear activity from current stack meaning go back previous
        }

        if (item?.itemId == R.id.save) {
            //var validateReviewsStatus = validateReviews()

            //if (validateReviewsStatus) {
                var radioButtonText = getRadioButtonText()
                var suitableYesOrNo = checkSuitableForChildren()

                val savingEditedMovieObj = intent.getSerializableExtra("editMovieObj") as MovieEntity
                Log.e("saving intent now back to view movie", "saving@@@@@@@@@@")
                savingEditedMovieObj.title = editNameOfMovie.text.toString()
                savingEditedMovieObj.overview = editDescription.text.toString()
                savingEditedMovieObj.releaseDate = editReleaseDate.text.toString()
                savingEditedMovieObj.language = radioButtonText
                savingEditedMovieObj.suitableAge = suitableYesOrNo
                var myIntent = Intent(this, ViewMovieActivity::class.java)
                myIntent.putExtra("editedObj", savingEditedMovieObj)
                //startActivity(myIntent)
                setResult(889, myIntent) // set data to be given back to viewMovie
                finish()

        }

        return super.onOptionsItemSelected(item)
    }


    fun checkSuitableForChildren(): String{
        var suitableForChildren = "No"

        if (!editCheckBoxSuitable.isChecked) {
            suitableForChildren = "Yes"
        }
        else{
            if (editCheckBoxLanguageUsed.isChecked && editCheckBoxViolence.isChecked) {
                suitableForChildren = "No (Violence, Language used)"
            }
            else if (editCheckBoxViolence.isChecked) {
                suitableForChildren = "No (Violence)"
            }
            else if (editCheckBoxLanguageUsed.isChecked) {
                suitableForChildren = "No (Language Used)"
            }
        }
        return suitableForChildren
    }


    fun getRadioButtonText() : String {
        var radioButtonText = ""
        var radioButtonId: Int = editRadioGroupLanguage.checkedRadioButtonId
        Log.e("radio button id " , radioButtonId.toString())
        val radio: RadioButton = findViewById(radioButtonId)
        radioButtonText = radio.text.toString()

        return radioButtonText
    }

}
