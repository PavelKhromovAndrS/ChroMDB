package com.example.chromdb.ui.screens.movie_history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.chromdb.R
import com.example.chromdb.adapter.TopRatedActionListener
import com.example.chromdb.adapter.TopRatedAdapter
import com.example.chromdb.databinding.MovieHistoryFragmentBinding
import com.example.chromdb.model.entities.rest_entities.top_rated.TopRatedMovieItem
import com.example.chromdb.ui.main.SECURE_URL
import com.example.chromdb.ui.screens.DetailsMovieFragment
import org.koin.android.ext.android.inject

class MovieHistoryFragment : Fragment() {

    private var _binding: MovieHistoryFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MovieHistoryViewModel by inject()
    private lateinit var topRatedAdapter: TopRatedAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MovieHistoryFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllHistory()
        viewModel.historyLiveData.observe(viewLifecycleOwner, {
            setTopRatedData(it)
        })

        topRatedAdapter = TopRatedAdapter(object : TopRatedActionListener {
            override fun onTopRatedDetails(movieItem: TopRatedMovieItem) {
                val bundle = Bundle().apply {
                    putParcelable(DetailsMovieFragment.ARG_TOP_RATED_MOVIE, movieItem)
                }
                findNavController().navigate(
                    R.id.action_mainFragment_to_detailsMovieFragment,
                    bundle
                )
            }
        })
    }

    private fun setTopRatedData(topRatedData: List<TopRatedMovieItem>) =
        with(binding) {
            topRatedAdapter.secureBaseUrl = SECURE_URL
            topRatedAdapter.topRatedItemList = topRatedData
            historyRv.adapter = topRatedAdapter
        }
}