package com.danishkhalid.TW.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.danishkhalid.TW.R
import com.danishkhalid.TW.model.SuperHero
import com.squareup.picasso.Picasso

class SuperHeroAdapter(private var items: List<SuperHero>,private val listener:OnItemClickListener):RecyclerView.Adapter<SuperHeroAdapter.ViewHolder>() {
    interface OnItemClickListener {
        fun onItemClick(superHero: SuperHero)
    }
    fun updateDataSet(newItems: List<SuperHero>) {
        items = newItems
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.superHeroIv)
        val superHeroNameTv: TextView = itemView.findViewById(R.id.superHeroNameTv)
        val superHeroRatingTv: TextView = itemView.findViewById(R.id.superHeroRatingTv)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val superHero = items[position]
                    listener.onItemClick(superHero)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.superhero_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        Picasso.get().load(item.superHeroimg).into(holder.imageView)

        holder.superHeroNameTv.text = item.superHeroName

        holder.superHeroRatingTv.text = getRatingText(item.superHeroRating)

    }

    private fun getRatingText(rating: Float): String {
        return when {
            rating == 1.0f -> "Normal"
            rating == 2.0f -> "Very Good"
            rating == 3.0f -> "Awesome"
            else -> ""
        }
    }
}