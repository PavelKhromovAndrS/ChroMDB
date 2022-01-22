package com.example.chromdb.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.chromdb.R
import com.example.chromdb.adapter.MovieAdapter
import com.example.chromdb.adapter.UserActionListener
import com.example.chromdb.databinding.MainFragmentBinding
import com.example.chromdb.model.AppState
import com.example.chromdb.model.entities.MovieItem
import com.example.chromdb.showSnackBar
import com.example.chromdb.ui.screens.DetailsMovieFragment
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModel()
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val observer = Observer<AppState> {
            renderData(it)
        }
        viewModel.getLiveData().observe(viewLifecycleOwner, observer)
        viewModel.getMovie()
    }

    private fun renderData(appState: AppState) = with(binding) {
        when (appState) {
            is AppState.Success -> {
                val movieData = appState.movieData
                setData(movieData)
            }
            is AppState.Loading -> {
                Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()

            }
            is AppState.Error -> {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setData(movieData: List<MovieItem>) = with(binding) {
        val layoutManager = GridLayoutManager(requireContext(), 3)
        adapter = MovieAdapter(object : UserActionListener {
            override fun onMovieDetails(movieItem: MovieItem) {
                view?.showSnackBar(R.string.extension)
                val bundle = Bundle().apply {
                    putParcelable(DetailsMovieFragment.ARG_MOVIE, movieItem)
                }
                findNavController().navigate(
                    R.id.action_mainFragment_to_detailsMovieFragment,
                    bundle
                )
            }
        })
        adapter.movieList = movieData
        binding.movieListRv.layoutManager = layoutManager
        binding.movieListRv.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}