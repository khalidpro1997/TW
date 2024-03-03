package com.danishkhalid.TW

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.danishkhalid.TW.model.SuperHero
import com.squareup.picasso.Picasso

class SuperHeroDetailActivity : AppCompatActivity() {

    private lateinit var myToolbar: Toolbar

    private lateinit var backButton: ImageView
    private lateinit var superHeroNameTitleTv: TextView
    private lateinit var superHeroIv: ImageView
    private lateinit var superHeroratingBar: RatingBar
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_super_hero_detail)

        backButton = findViewById(R.id.backButton)
        superHeroNameTitleTv = findViewById(R.id.superHeroNameTitle)
        superHeroIv = findViewById(R.id.superHeroImage)

        superHeroratingBar = findViewById(R.id.ratingBar)

        myToolbar = findViewById(R.id.my_toolbar)
        setSupportActionBar(myToolbar)

        backButton.setOnClickListener {
            onBackPressed()
        }
        sharedPreferences = Avengers.preferences
        val superHero = intent.getSerializableExtra("superHero") as SuperHero

        superHeroNameTitleTv.text = superHero.superHeroName
        Picasso.get().load(superHero.superHeroimg).into(superHeroIv)

        val superheroRatingKey = superHero.superHeroName.replace("\\s".toRegex(), "")
        superHeroratingBar.rating = sharedPreferences.getFloat(superheroRatingKey, 0.0f)

        superHeroratingBar.setOnRatingBarChangeListener { _, rating, _ ->
            if (superHero.superHeroName.equals("Super Man")){
                saveRatingToSharedPreferences(superHero.superHeroName,rating)

            } else if (superHero.superHeroName.equals("Hulk")){
                saveRatingToSharedPreferences(superHero.superHeroName,rating)

            } else if (superHero.superHeroName.equals("Iron Man")){
                saveRatingToSharedPreferences(superHero.superHeroName,rating)
            }
        }

    }

    private fun saveRatingToSharedPreferences(superheroName: String, rating: Float) {
        val superheroRatingKey = superheroName
        val editor = sharedPreferences.edit()
        editor.putFloat(superheroRatingKey, rating)
        editor.apply()
    }
}