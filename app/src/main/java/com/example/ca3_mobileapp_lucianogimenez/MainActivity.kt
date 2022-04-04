package com.example.ca3_mobileapp_lucianogimenez

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {

    private var option = "users"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked
            // Check which radio button was clicked
            when (view.getId()) {
                R.id.user_button ->
                    if (checked) {
                        val button = findViewById<RadioButton>(R.id.user_button)
                        button.setOnClickListener(View.OnClickListener() {
                            option = "users"
                            println(option)
                        })
                    }
                R.id.organization_button ->
                    if (checked) {
                        val buttonSecond = findViewById<RadioButton>(R.id.organization_button)
                        buttonSecond.setOnClickListener(View.OnClickListener() {
                            option = "orgs"
                            println(option)
                        })
                    }
            }
        }
    }

}