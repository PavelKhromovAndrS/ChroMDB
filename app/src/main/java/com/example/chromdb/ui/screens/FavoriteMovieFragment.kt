package com.example.chromdb.ui.screens

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast


import com.example.chromdb.data.api.GenresService
import com.example.chromdb.databinding.FragmentFavoriteMovieBinding
import com.example.chromdb.model.entities.rest_entities.genres.Genre


class FavoriteMovieFragment : Fragment() {
    companion object {
        const val GENRE_ARG = "GENRE_ARG"
        const val GENRE_BROADCAST_INTENT_FILTER = "GENRE_BROADCAST_INTENT_FILTER"
    }

    private var _binding: FragmentFavoriteMovieBinding? = null
    private val binding get() = _binding!!
    private val genresReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            intent.let {
                Toast.makeText(context, it?.getStringExtra(GENRE_ARG), Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context?.registerReceiver(genresReceiver, IntentFilter(GENRE_BROADCAST_INTENT_FILTER))
    }

    override fun onDestroy() {
        context?.unregisterReceiver(genresReceiver)
        super.onDestroy()
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.serviceButton.setOnClickListener(View.OnClickListener {
            context?.let {
                it.startService(Intent(it, GenresService::class.java).apply {
                })
            }

        })
    }
}