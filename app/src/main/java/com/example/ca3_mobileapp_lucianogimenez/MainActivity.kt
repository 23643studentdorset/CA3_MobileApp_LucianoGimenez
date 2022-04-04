package com.example.ca3_mobileapp_lucianogimenez

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lateinit var option: String



        if (findViewById<RadioButton>(R.id.organization_button).isChecked){
            option = "orgs"
        }else{
            option = "users"
        }

        val buttonClick = findViewById<Button>(R.id.search_button)
        buttonClick.setOnClickListener {
            Log.i("lucho", findViewById<EditText>(R.id.input).text.toString())
            if (findViewById<EditText>(R.id.input).text.toString() != "") {
                val userInput = findViewById<EditText>(R.id.input).text.toString()
                val intent = Intent(this, SecondaryActivity::class.java)
                intent.putExtra("user_input", userInput)
                intent.putExtra("radio_option", option)
                startActivity(intent)
            }else{
                Toast.makeText(this, "You have to write a name", Toast.LENGTH_SHORT).show()
            }
        }

    }
}