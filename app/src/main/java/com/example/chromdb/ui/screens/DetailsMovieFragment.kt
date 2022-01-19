package com.example.chromdb.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.chromdb.R
import com.example.chromdb.databinding.FragmentDetailsMovieBinding
import com.example.chromdb.model.entities.MovieItem

class DetailsMovieFragment : Fragment() {
    private var _binding: FragmentDetailsMovieBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<MovieItem>(ARG_MOVIE)?.let {
            with(binding) {
                titleMovieTv.text = (it.title)
                descriptionMovieTv.text = (it.description)
                Glide.with(detailsImageMovieIv.context)
                    .load(it.image)
                    .placeholder(R.drawable.ic_baseline_movie_24)
                    .into(detailsImageMovieIv)
                ratingTv.text = (it.rating.toString())
                yearTv.text = (it.year.toString())
            }
        }
    }

    companion object {
        const val ARG_MOVIE = "ARG_MOVIE"
    }
}