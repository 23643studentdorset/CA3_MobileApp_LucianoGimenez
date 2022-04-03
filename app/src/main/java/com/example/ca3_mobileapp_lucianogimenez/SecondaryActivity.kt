package com.example.ca3_mobileapp_lucianogimenez

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

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
    }
}