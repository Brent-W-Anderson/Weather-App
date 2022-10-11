package com.example.weather_app

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.weather_app.databinding.FragmentCurrentConditionsBinding
import com.squareup.picasso.Picasso

class CurrentConditions : Fragment( R.layout.fragment_current_conditions ) {

    private lateinit var binding: FragmentCurrentConditionsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCurrentConditionsBinding.bind(view)

        // load image in here..
        val imageView: ImageView = view.findViewById(R.id.sunIcon)
        Picasso.get().load("https://openweathermap.org/img/wn/01d@4x.png").into(imageView)
    }
}