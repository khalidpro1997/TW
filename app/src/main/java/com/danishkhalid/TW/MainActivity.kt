package com.danishkhalid.TW

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.danishkhalid.TW.Adapter.SuperHeroAdapter
import com.danishkhalid.TW.model.SuperHero

class MainActivity : AppCompatActivity(), SuperHeroAdapter.OnItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var toolbar: Toolbar
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var items: List<SuperHero>
    private lateinit var adapter: SuperHeroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.superHeroRv)
        toolbar = findViewById(R.id.toolbar)
        sharedPreferences = Avengers.preferences

        val superManRating = getRating("Super Man")
        val hulkRating = getRating("Hulk")
        val ironManRating = getRating("Iron Man")

        items = listOf(
            SuperHero(R.drawable.superman, "Super Man", superManRating),
            SuperHero(R.drawable.avenger_hulk, "Hulk", hulkRating),
            SuperHero(R.drawable.avenger_ironman, "Iron Man", ironManRating)
        )

        adapter = SuperHeroAdapter(items, this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    override fun onItemClick(superHero: SuperHero) {
        val intent = Intent(this, SuperHeroDetailActivity::class.java)
        intent.putExtra("superHero", superHero)
        startActivity(intent)
    }

    private fun getRating(superheroName: String): Float {
        val superheroRatingKey = superheroName
        Log.d("SuperHeroRatings", "Rating for $superheroName: ${sharedPreferences.getFloat(superheroRatingKey, -1.0f)}")
        return sharedPreferences.getFloat(superheroRatingKey, -1.0f)
    }

    override fun onResume() {
        super.onResume()
        items = items.map {
            SuperHero(it.superHeroimg, it.superHeroName, getRating(it.superHeroName))
        }

        adapter.updateDataSet(items)
        adapter.notifyDataSetChanged()
    }
}