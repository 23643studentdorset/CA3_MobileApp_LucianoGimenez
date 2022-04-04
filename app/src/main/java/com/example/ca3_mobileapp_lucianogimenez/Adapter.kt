package com.example.ca3_mobileapp_lucianogimenez

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class Adapter (private val repoList : ArrayList<Repos>, val context: Context):RecyclerView.Adapter<CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.list_item_2, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val currentItem = repoList[position]
        holder.name.text = currentItem.name
        holder.visibility.text = currentItem.visibility
        holder.description.text = currentItem.description
        holder.language.text = currentItem.language
        if (currentItem.language == "C"){
            holder.languagecolor.setColorFilter(ContextCompat.getColor(context , R.color.purple_500))
        }
        holder.stargazersCount.text = formatNum(currentItem.stargazers_count)
        holder.forksCount.text = formatNum(currentItem.forks_count)
    }

    override fun getItemCount(): Int {
        return repoList.size
    }

}

class CustomViewHolder(private val view: View):RecyclerView.ViewHolder(view){
    val name: TextView = itemView.findViewById(R.id.name)
    val visibility: TextView = itemView.findViewById(R.id.visibility)
    val description: TextView = itemView.findViewById(R.id.description)
    val language: TextView = itemView.findViewById(R.id.language)
    val stargazersCount: TextView = itemView.findViewById(R.id.stargazers_count)
    val forksCount: TextView = itemView.findViewById(R.id.forks_count)
    val languagecolor:ImageView = itemView.findViewById(R.id.colour_language)



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




