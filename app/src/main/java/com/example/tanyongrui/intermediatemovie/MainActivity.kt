//Update your package here
package com.example.tanyongrui.intermediatemovie

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //register the menu from main.xml into activity_main.xml
        // since this MainActivity kotlin is for activity_main.xml
        registerForContextMenu(landingPageText) //match id of text in order for pop up to work

    }

    //once add text is press, function logic below
    override fun onContextItemSelected(item: MenuItem?): Boolean {

        if (item?.itemId == 1001) {
            //finish() //end the screen...
            // call intent to move to add Movie screen
            var myIntent = Intent(this, AddMovieActivity::class.java)
            startActivity(myIntent)
        }

        return super.onContextItemSelected(item)
    }

    //long press, the add text will display
    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)

        if (v?.id == R.id.landingPageText) {
            menu?.add(1,1001,1,"Add")
        }

    }


}


