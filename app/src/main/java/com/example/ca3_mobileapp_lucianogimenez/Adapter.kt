package com.example.ca3_mobileapp_lucianogimenez

import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter (private val repoList : ArrayList<Repos>):RecyclerView.Adapter<CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.list_item_2, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val currentItem = repoList[position]
        holder.name.text = currentItem.name
        holder.visibility.text = currentItem.visibility
        holder.description.text = currentItem.description
        holder.language.text = currentItem.language
        holder.stargazers_count.text = currentItem.stargazers_count.toString()
        holder.forks_count.text = currentItem.forks_count.toString()
    }

    override fun getItemCount(): Int {
        return repoList.size
    }

}

class CustomViewHolder(val view: View):RecyclerView.ViewHolder(view){
    val name: TextView = itemView.findViewById(R.id.name)
    val visibility: TextView = itemView.findViewById(R.id.visibility)
    val description: TextView = itemView.findViewById(R.id.description)
    val language: TextView = itemView.findViewById(R.id.language)
    val stargazers_count: TextView = itemView.findViewById(R.id.stargazers_count)
    val forks_count: TextView = itemView.findViewById(R.id.forks_count)


}