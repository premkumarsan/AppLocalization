package com.applocalization

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import java.util.*


class MainActivity : AppCompatActivity() {

    private var languageList = arrayOf("Select Language", "English", "Arabic", "Russian")

    var spinner: Spinner? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinner = findViewById(R.id.spinner)

        if (spinner != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, languageList
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner!!.adapter = adapter


            spinner!!.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {

                    when (parent.getItemAtPosition(position)) {
                        "English" -> {
                            setLocale(this@MainActivity, "en")
                            moveActivity()
                        }

                        "Arabic" -> {
                            setLocale(this@MainActivity, "ar")
                            moveActivity()
                        }

                        "Russian" -> {
                            setLocale(this@MainActivity, "ru")
                            moveActivity()
                        }
                    }

                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

    }

    private fun moveActivity() {
        val intent = Intent(this@MainActivity, MainTwo::class.java)
        startActivity(intent)
    }


    private fun setLocale(activity: Activity, languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val resources: Resources = activity.resources
        val config: Configuration = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
    }


}