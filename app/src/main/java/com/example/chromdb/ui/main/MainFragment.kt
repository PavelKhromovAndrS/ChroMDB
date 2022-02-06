package com.example.chromdb.ui.main

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.chromdb.R
import com.example.chromdb.adapter.TopRatedAdapter
import com.example.chromdb.adapter.PopularAdapter
import com.example.chromdb.adapter.PopularItemActionListener
import com.example.chromdb.adapter.TopRatedActionListener
import com.example.chromdb.databinding.MainFragmentBinding
import com.example.chromdb.model.entities.rest_entities.popular.PopularMovieItem
import com.example.chromdb.model.entities.rest_entities.top_rated.TopRatedMovieItem
import com.example.chromdb.ui.screens.DetailsMovieFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val IS_ADULT_KEY = "IS_ADULT_KEY"


class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModel()
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var topRatedAdapter: TopRatedAdapter
    private lateinit var popularAdapter: PopularAdapter
    private lateinit var secureBaseUrl: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadData()

        viewModel.configLiveData.observe(viewLifecycleOwner, Observer {
            secureBaseUrl = it.secure_base_url.toString() + (it.backdrop_sizes.get(3))
        })

        activity?.let {
            if (it.getPreferences(Context.MODE_PRIVATE).getBoolean(IS_ADULT_KEY, false)) {

                showAdultContent()
                adultCb.isChecked = true
            } else {
                viewModel.popularLiveData.observe(viewLifecycleOwner, Observer {
                    it.let { it1 ->
                        setPopularData(it1, secureBaseUrl)
                    }
                })
                viewModel.topRatedLiveData.observe(viewLifecycleOwner, Observer {
                    it.let { it1 -> setTopRatedData(it1, secureBaseUrl) }
                })
            }


            adultCb.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    isAdultContent(true)
                } else {
                    isAdultContent(false)
                }
            }
        }

        popularAdapter = PopularAdapter(object : PopularItemActionListener {
            override fun onPopularItemDetails(movieItem: PopularMovieItem) {
                val bundle = Bundle().apply {
                    putParcelable(DetailsMovieFragment.ARG_POP_MOVIE, movieItem)
                }
                findNavController().navigate(
                    R.id.action_mainFragment_to_detailsMovieFragment,
                    bundle
                )
            }
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

    private fun isAdultContent(isAdult: Boolean) {
        activity?.let {
            with(it.getPreferences(Context.MODE_PRIVATE).edit()) {
                putBoolean(IS_ADULT_KEY, isAdult)
                apply()
            }
        }
    }

    private fun showAdultContent() {
        viewModel.topRatedLiveData.observe(viewLifecycleOwner, Observer {
            it.let {
                it[1].adult = true
                var notAdultContent: MutableList<TopRatedMovieItem> =
                    mutableListOf<TopRatedMovieItem>()
                for (movie in it) {
                    if (movie.adult == true)
                        notAdultContent.add(movie)
                }
                setTopRatedData(notAdultContent, secureBaseUrl)
            }
        })

    }


    private fun setPopularData(popularData: List<PopularMovieItem>, baseUrl: String) =
        with(binding) {
            popularAdapter.secureBaseUrl = baseUrl
            popularAdapter.popularItemList = popularData
            popularListRv.adapter = popularAdapter
        }

    private fun setTopRatedData(topRatedData: List<TopRatedMovieItem>, baseUrl: String) =
        with(binding) {
            topRatedAdapter.secureBaseUrl = baseUrl
            topRatedAdapter.topRatedItemList = topRatedData
            topRatedListRv.adapter = topRatedAdapter
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}