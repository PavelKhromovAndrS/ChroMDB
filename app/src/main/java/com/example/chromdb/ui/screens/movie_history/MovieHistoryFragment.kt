package com.example.chromdb.ui.screens.movie_history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chromdb.R
import com.example.chromdb.adapter.ActionListener
import com.example.chromdb.adapter.MovieAdapter
import com.example.chromdb.databinding.MovieHistoryFragmentBinding
import com.example.chromdb.model.entities.rest_entities.top_rated.MovieItem
import com.example.chromdb.ui.main.SECURE_URL
import com.example.chromdb.ui.screens.DetailsMovieFragment
import org.koin.android.ext.android.inject

class MovieHistoryFragment : Fragment() {

    private var _binding: MovieHistoryFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MovieHistoryViewModel by inject()
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MovieHistoryFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllHistory()
        viewModel.historyLiveData.observe(viewLifecycleOwner, {
            setMovieData(it)
        })
        historyRv.layoutManager = LinearLayoutManager(context)

        movieAdapter = MovieAdapter(object : ActionListener {
            override fun onMovieDetails(movieItem: MovieItem) {
                val bundle = Bundle().apply {
                    putParcelable(DetailsMovieFragment.ARG_MOVIE, movieItem)
                }
                findNavController().navigate(
                    R.id.action_movieHistoryFragment_to_detailsMovieFragment,
                    bundle
                )
            }
        })
    }

    private fun setMovieData(data: List<MovieItem>) =
        with(binding) {
            movieAdapter.secureBaseUrl = SECURE_URL
            movieAdapter.itemList = data
            historyRv.adapter = movieAdapter
        }
}