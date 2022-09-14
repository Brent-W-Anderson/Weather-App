package com.example.weather_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //load image in here..
        val imageView: ImageView = findViewById( R.id.sunIcon )
        Picasso.get().load("https://openweathermap.org/img/wn/01d@4x.png").into(imageView)
    }
}