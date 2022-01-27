package com.example.chromdb.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.chromdb.R
import com.example.chromdb.databinding.FragmentDetailsMovieBinding
import com.example.chromdb.model.entities.rest_entities.popular.PopularMovieItem
import com.example.chromdb.model.entities.rest_entities.top_rated.TopRatedMovieItem

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

        arguments?.getParcelable<PopularMovieItem>(ARG_POP_MOVIE)?.let {
            with(binding) {
                titleMovieTv.text = (it.title)
                descriptionMovieTv.text = (it.overview)
                Glide.with(detailsImageMovieIv.context)
                    .load(ARG_BASE_URL + it.poster_path)
                    .placeholder(R.drawable.ic_baseline_movie_24)
                    .into(detailsImageMovieIv)
                ratingTv.text = (it.vote_average.toString())
                yearTv.text = (it.release_date)
            }
        }

        arguments?.getParcelable<TopRatedMovieItem>(ARG_TOP_RATED_MOVIE)?.let {
            with(binding) {
                titleMovieTv.text = (it.title)
                descriptionMovieTv.text = (it.overview)
                Glide.with(detailsImageMovieIv.context)
                    .load(ARG_BASE_URL + it.poster_path)
                    .placeholder(R.drawable.ic_baseline_movie_24)
                    .into(detailsImageMovieIv)
                ratingTv.text = (it.vote_average.toString())
                yearTv.text = (it.release_date)
            }
        }
    }

    companion object {
        const val ARG_POP_MOVIE = "ARG_POP_MOVIE"
        const val ARG_TOP_RATED_MOVIE = "ARG_TOP_RATED_MOVIE"
        const val ARG_BASE_URL = "https://image.tmdb.org/t/p/original"
    }
}