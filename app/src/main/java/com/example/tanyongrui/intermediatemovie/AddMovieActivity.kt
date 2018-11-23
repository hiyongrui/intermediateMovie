package com.example.tanyongrui.intermediatemovie

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_movie.* // import for all the checkBoxSuitable etc to work
import kotlinx.android.synthetic.main.activity_main.*

class AddMovieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_movie)

        //registerForContextMenu(clearEntries)

        checkBoxSuitable.setOnClickListener {
            if (checkBoxSuitable.isChecked == true) {
                //tvMyTextDisplay.text = "CheckBox has been checked@@@"
                checkBoxViolence.setVisibility(View.VISIBLE)
                checkBoxLanguageUsed.setVisibility(View.VISIBLE)
            } else {
                //tvMyTextDisplay.text = "CheckBox has unchecked!!!!"
                checkBoxViolence.setVisibility(View.GONE)
                checkBoxLanguageUsed.setVisibility(View.GONE)
                checkBoxViolence.isChecked = false
                checkBoxLanguageUsed.isChecked = false
            }
        }

        /*radioGroupLanguage.setOnCheckedChangeListener { group, checkedId ->
            //val radio:RadioButton = findViewById(checkedId)
            //radio.setError(null) //this only removes error if clicked radio btn 4, since error is on btn4
            rbtnButton4.setError(null)
        } */
        radioGroupLanguage.setOnCheckedChangeListener() { radioGroup: RadioGroup, i: Int ->
            rbtnButton4.setError(null) //remove error that was set once clicked on any other radio
        }

    } //end of override fun create method

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.add_menu, menu) //inflate the main.xml from menu directory

        return super.onCreateOptionsMenu(menu)
    }

    //when clear entries is clicked
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (item?.itemId == R.id.clearEntries) {
            Toast.makeText(this, "Clear entries", Toast.LENGTH_SHORT).show()

            val listOfText = mutableListOf(nameOfMovie, description, releaseDate)
            val listOfCheckBox = mutableListOf(checkBoxSuitable, checkBoxViolence, checkBoxLanguageUsed)
            for (i in 0..listOfText.size - 1) {
                listOfText[i].setText("")
                listOfCheckBox[i].isChecked = false
            }
            var radioButtonId: Int = radioGroupLanguage.checkedRadioButtonId
            val radio: RadioButton = findViewById(radioButtonId)
            radio.setChecked(false)
            radioGroupLanguage.check(R.id.rbtnButton1)
            Log.e("radio button id uncheck ---> ", "radio id " + radioButtonId)

            checkBoxViolence.setVisibility(View.GONE)
            checkBoxLanguageUsed.setVisibility(View.GONE)
        }
        else{

        }
        return super.onOptionsItemSelected(item)
    }



    fun checkBoxViolenceValiation(): String {
        var checkboxViolenceText = ""
        //var checkYes = checkBoxViolence.isChecked()
        if (checkBoxViolence.isChecked()) {
            checkboxViolenceText = "\n " + checkBoxViolence.text.toString()
        }
        Log.d("checkyestag","checkyes value log print is " + checkboxViolenceText)
        println("hello this printlnss is working!!! ")
        //System.out.printf("hello this system println ")
        return checkboxViolenceText
    }
    fun checkBoxLanguageValidation() : String {
        var checkBoxLanguageText = ""
        if (checkBoxLanguageUsed.isChecked()) {
            checkBoxLanguageText = "\n " + checkBoxLanguageUsed.text.toString()
        }
        return checkBoxLanguageText
    }

    fun checkSuitableValidation() : String {
        var checkSuitableToast = ""
        var suitableIsChecked = false;
        // practical assignment logic wrong, if not suitable for all audience,
        // means toast should display suitable for all ages = false instead..
        if (!checkBoxSuitable.isChecked()) {
            suitableIsChecked = true;
        }
        if (!suitableIsChecked) {
            if (!checkBoxViolence.isChecked && !checkBoxLanguageUsed.isChecked) {
                checkSuitableToast = "Suitable for all ages = " + suitableIsChecked
            }
            else {
                checkSuitableToast = "Suitable for all ages = " + suitableIsChecked + "\n Reason:"
            }
        }
        else{
            checkSuitableToast = "Suitable for all ages = " + suitableIsChecked
        }

        return checkSuitableToast
    }

    fun getRadioButtonText() : String {
        var radioButtonText = ""
        var radioButtonId: Int = radioGroupLanguage.checkedRadioButtonId
        Log.e("radio button id " , radioButtonId.toString())
        val radio: RadioButton = findViewById(radioButtonId)
        radioButtonText = radio.text.toString()

        return radioButtonText
    }

    fun btnValidate(menuItem: MenuItem) { // previously v:View
        var statusOfValidation = validationDone() //validate all fields empty/no
        System.out.println("validation done fail/pass " + statusOfValidation)
        if (statusOfValidation) {
            var checkBoxVio = checkBoxViolenceValiation()
            var checkBoxLanguage = checkBoxLanguageValidation()
            var checkSuitableToast = checkSuitableValidation()
            var radioButtonText = getRadioButtonText()

            Toast.makeText(
                this," Title = " + nameOfMovie.text +
                        "\n Overview = " + description.text +
                        "\n Release date = " + releaseDate.text +
                        "\n Language = " + radioButtonText +
                        "\n " + checkSuitableToast +
                        checkBoxVio + checkBoxLanguage, Toast.LENGTH_LONG
            ).show()
            //call movie entity class, create object, put in the values and
            // pass the object into the next activities through intent
            val movieEntityObject = MovieEntity()
            movieEntityObject.title = nameOfMovie.text.toString()
            movieEntityObject.overview = description.text.toString()
            movieEntityObject.releaseDate = releaseDate.text.toString()
            movieEntityObject.language = radioButtonText
            var myIntent = Intent(this, ViewMovieActivity::class.java)
            myIntent.putExtra("callThisShit", movieEntityObject)
            startActivity(myIntent)
        }

    } // end of btnvalidate method

    private fun validationDone(): Boolean {
        var statusOfValidation = true;
        val list = mutableListOf(nameOfMovie,description, releaseDate)
        Log.d("validating list", " list is " + list)
        Log.d("length of list", "length/size = " + list.size)
        for (i in 0..list.size-1) {
            System.out.println("list i val " + i + " value = " + list[i])
            System.out.println("text values in list : " + list[i].text)
            if (list[i].text.toString() == "") {
                System.out.println("list[i] is empty !!! " + list[i].text)
                statusOfValidation = false
                list[i].setError("Field empty")
            }
        }
        if (radioGroupLanguage.checkedRadioButtonId == -1) {
            statusOfValidation = false
            rbtnButton4.setError("radio empty")
        }
        return statusOfValidation
    }


}
