package com.example.dog_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class Vote(
    private val context: MainActivity
) : Fragment() {

    private lateinit var imageView: ImageView
    private lateinit var likeButton: Button
    private lateinit var disLikeButton: Button

    private val imageResources = listOf(
        R.drawable.dog1,
        R.drawable.dog2,
        R.drawable.dog3
    )

    private var currentIndex = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_vote, container, false)

        // Initialize views
        imageView = view.findViewById(R.id.imageView)
        likeButton = view.findViewById(R.id.buttonLike)
        disLikeButton = view.findViewById(R.id.buttonDislike)

        // Initial image
        imageView.setImageResource(imageResources[currentIndex])

        likeButton.setOnClickListener {
            // Change image on button click
            showNextImage()
        }

        disLikeButton.setOnClickListener {
            // Change image on button click
            showNextImage()
        }

        return view
    }

    private fun showNextImage() {
        currentIndex = (currentIndex + 1) % imageResources.size
        imageView.setImageResource(imageResources[currentIndex])
    }
}