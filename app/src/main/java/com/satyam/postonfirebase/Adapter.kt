package com.satyam.postonfirebase

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.posts.view.*
import kotlin.collections.ArrayList

internal class adapter(
    var context: Context,
    var name: ArrayList<Product>


) :
    RecyclerView.Adapter<adapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(context)
         val view = layoutInflater.inflate(R.layout.posts, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var pro =Product()
        pro =name.get(position)
        val firstName =pro.name
        val lastName = pro.last
        val url = pro.url



       holder.textView.text = "$firstName $lastName"
        Glide.with(context)
            .load(url)
            .into(holder.imageView)

    }

    override fun getItemCount(): Int {
        return name.size
    }

    inner class MyViewHolder(itemView: View) : ViewHolder(itemView) {
        var textView: TextView = itemView.nameGetting
        var imageView: ImageView = itemView.imageGetting

    }

}
