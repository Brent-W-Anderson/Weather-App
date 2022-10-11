package com.example.weather_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.example.weather_app.databinding.FragmentForecastBinding
import com.squareup.picasso.Picasso

class forecast : Fragment( R.layout.fragment_forecast ) {

    private lateinit var binding: FragmentForecastBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentForecastBinding.bind(view)

        binding.activityButton.setOnClickListener {
            findNavController().navigate(R.id.action_forecast_to_currentConditions)
        }

        // load image in here..
        val imageView: ImageView = view.findViewById(R.id.sunIcon)
        Picasso.get().load("https://openweathermap.org/img/wn/01d@4x.png").into(imageView)
    }
}