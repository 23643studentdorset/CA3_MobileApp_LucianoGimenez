package com.example.ca3_mobileapp_lucianogimenez

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ca3_mobileapp_lucianogimenez.R.drawable.ic_baseline_error_outline_24
import com.google.gson.GsonBuilder
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import okhttp3.*
import java.io.IOException

class SecondaryActivity : AppCompatActivity() {

    private lateinit var newRecyclerView : RecyclerView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_item)

        val bundle : Bundle ?= intent.extras
        val input = bundle?.getString("user_input")
        val option = bundle?.getString("radio_option")

        println("option: $option")
        //println("input: $input")

        newRecyclerView = findViewById(R.id.recycler_view)
        newRecyclerView.layoutManager = LinearLayoutManager(this)

        fetchJsonData(input!!,option!!)
        fetchJsonRepos(input!!,option!!)

    }

    private fun fetchJsonData(input: String, option: String) {
        val url = "https://api.github.com/$option/$input"
        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {

            @SuppressLint("UseCompatLoadingForDrawables")
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val body = response.body?.string()
                    //println(body)
                    val gson = GsonBuilder().create()
                    val userData = gson.fromJson(body, UserData::class.java)
                    //println(userData)
                    runOnUiThread {
                        val avatarPhoto = findViewById<ImageView>(R.id.photo)
                        val avatarUrl = userData.avatar_url
                        Picasso.get()
                            .load(avatarUrl)
                            .error(getDrawable(ic_baseline_error_outline_24)!!)
                            .memoryPolicy(MemoryPolicy.NO_CACHE)
                            .into(avatarPhoto)
                        findViewById<TextView>(R.id.user_name).text = userData.name
                        findViewById<TextView>(R.id.login).text = userData.login
                        findViewById<TextView>(R.id.followers).text = formatNum(userData.followers)
                        findViewById<TextView>(R.id.following).text = formatNum(userData.following)
                        findViewById<TextView>(R.id.company).text = userData.company
                        findViewById<TextView>(R.id.location).text = userData.location
                    }
                }
            }
            override fun onFailure(call: Call, e: IOException) {
                println("failed to execute")
            }
        })
    }

    private fun fetchJsonRepos(input: String, option: String) {
        val url = "https://api.github.com/$option/$input/repos"
        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()
        client.run {
            newCall(request).enqueue(object : Callback {

                override fun onFailure(call: Call, e: IOException) {
                    println("no repos?")
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        val body = response.body?.string()
                        //println("repos:$body")
                        val gson = GsonBuilder().create()
                        val userRepoData = gson.fromJson(body, RepoList::class.java)
                        runOnUiThread {
                            newRecyclerView.adapter = Adapter(userRepoData)
                        }
                    }
                }

            })
        }
    }
    fun formatNum(num: Int?): String {
        var string = ""
        if (num != null){
            if (num > 1000)  {
                string += (num / 1000).toString()
                string += "k"
            }else string += num.toString()
        }
        return string
    }
}

