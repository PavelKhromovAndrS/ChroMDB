package com.example.chromdb.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.chromdb.R
import com.example.chromdb.adapter.MovieAdapter
import com.example.chromdb.adapter.ActionListener
import com.example.chromdb.databinding.MainFragmentBinding
import com.example.chromdb.model.entities.rest_entities.top_rated.MovieItem
import com.example.chromdb.ui.screens.DetailsMovieFragment
import com.example.chromdb.ui.screens.GoogleMapsFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val APP_PASSWORD = ""
const val SECURE_URL = "https://image.tmdb.org/t/p/original"

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModel()
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var movieAdapter1: MovieAdapter
    private lateinit var movieAdapter2: MovieAdapter

    val movieListener = object : ActionListener {
        override fun onMovieDetails(movieItem: MovieItem) {

            viewModel.loadHistoryData(movieItem)
            val bundle = Bundle().apply {
                putParcelable(DetailsMovieFragment.ARG_MOVIE, movieItem)
            }
            findNavController().navigate(
                R.id.action_mainFragment_to_detailsMovieFragment,
                bundle
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        movieAdapter1 = MovieAdapter(movieListener)
        movieAdapter2 = MovieAdapter(movieListener)
        viewModel.popularLiveData.observe(viewLifecycleOwner, Observer {
            setData(it, movieAdapter1, popularListRv)

        })
        viewModel.topRatedLiveData.observe(viewLifecycleOwner, Observer {
            setData(it, movieAdapter2, topRatedListRv)

        })
        viewModel.loadData()


    }

    private fun setData(data: List<MovieItem>, adapter: MovieAdapter, rv: RecyclerView) =
        with(binding) {
            adapter.itemList = data
            rv.adapter = adapter
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object {
        fun newInstance() = MainFragment()
    }
}