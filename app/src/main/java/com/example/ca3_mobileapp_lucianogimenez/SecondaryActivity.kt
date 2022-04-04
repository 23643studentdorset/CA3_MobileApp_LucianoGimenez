package com.example.ca3_mobileapp_lucianogimenez

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import okhttp3.*
import java.io.IOException
import java.text.NumberFormat

class SecondaryActivity : AppCompatActivity() {

    private lateinit var newRecyclerView : RecyclerView
    private lateinit var newArrayList : ArrayList<Repos>
    lateinit var nameInfo : Array<String>
    lateinit var visibilityInfo : Array<String>
    lateinit var descriptionInfo : Array<String>
    lateinit var languageInfo : Array<String>
    lateinit var stargazers_count : Array<Int>
    lateinit var forks_count : Array<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_item)

        newRecyclerView = findViewById(R.id.recycler_view)
        newRecyclerView.layoutManager = LinearLayoutManager(this)

        fetchJsonData()
        fetchJsonRepos()

        /*
        nameInfo = arrayOf("linux","test-tlb","subsurface-for-dirk")
        visibilityInfo = arrayOf("Public", "Public", "Public")
        descriptionInfo = arrayOf("Linux kernel source tree","Stupid memory latency and TB tester","Do not use - the real upstream is Subsurface-divelog/subsurface")
        languageInfo = arrayOf("C","C","C++")
        stargazers_count = arrayOf(129000, 445, 199)
        forks_count = arrayOf(42200, 159, 53)

        newRecyclerView = findViewById(R.id.recycler_view)
        newRecyclerView.layoutManager = LinearLayoutManager(this)
        newArrayList = arrayListOf<Repos>()
        for(i in nameInfo.indices){
            val repository = Repos (
                nameInfo[i], visibilityInfo[i], descriptionInfo[i], languageInfo[i], stargazers_count[i], forks_count[i])
            newArrayList.add(repository)
        }

        newRecyclerView.adapter = Adapter(newArrayList)
        */
    }

    fun fetchJsonData() {
        val url = "https://api.github.com/users/torvalds"
        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                //println(body)
                val gson = GsonBuilder().create()
                val userData = gson.fromJson(body, UserData::class.java)
                //println(userData)
                runOnUiThread {
                    val avatar_photo = findViewById<ImageView>(R.id.photo)
                    val avatar_url = userData.avatar_url
                    Picasso.get()
                        .load(avatar_url)
                        .error(getDrawable(R.drawable.ic_baseline_error_outline_24)!!)
                        .memoryPolicy(MemoryPolicy.NO_CACHE)
                        .into(avatar_photo)
                    findViewById<TextView>(R.id.user_name).text = userData.name
                    findViewById<TextView>(R.id.login).text = userData.login
                    findViewById<TextView>(R.id.followers).text = formatNum(userData.followers)
                    findViewById<TextView>(R.id.following).text = formatNum(userData.following)
                    findViewById<TextView>(R.id.company).text = userData.company
                    findViewById<TextView>(R.id.location).text = userData.location
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                println("failled to execute")
            }
        })
    }
    fun formatNum(num: Int?): String {
        var string = ""
        if (num != null){
            if (num > 1000)  {
                string += (num / 1000).toString()
                string += "k"
            }else{
                string += num.toString()
            }
        }
        return string
    }

    fun fetchJsonRepos() {
        val url = "https://api.github.com/users/torvalds/repos"
        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()
        client.run {
            newCall(request).enqueue(object : Callback {

                override fun onFailure(call: Call, e: IOException) {
                    println("no repos")
                }

                override fun onResponse(call: Call, response: Response) {
                    val body = response.body?.string()
                    println("repos:$body")

                    val gson = GsonBuilder().create()
                    val userRepoData = gson.fromJson(body, RepoList::class.java)
                    runOnUiThread{
                        newRecyclerView.adapter = Adapter(userRepoData)
                    }

                }

            })
        }
    }
}

