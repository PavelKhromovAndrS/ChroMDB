package com.example.chromdb.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chromdb.databinding.MovieItemBinding
import com.example.chromdb.model.entities.rest_entities.top_rated.TopRatedMovieItem

interface TopRatedActionListener {
    fun onTopRatedDetails(movieItem: TopRatedMovieItem)
}

class TopRatedAdapter(private val topRatedActionListener: TopRatedActionListener) :
    RecyclerView.Adapter<TopRatedAdapter.TopRatedViewHolder>(), View.OnClickListener {

    var secureBaseUrl: String = ""
    var topRatedItemList = emptyList<TopRatedMovieItem>()

    override fun onClick(v: View) {
        val movie = v.tag as TopRatedMovieItem
        topRatedActionListener.onTopRatedDetails(movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieItemBinding.inflate(inflater, parent, false)
        binding.root.setOnClickListener(this)
        return TopRatedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopRatedViewHolder, position: Int) {

        val movieItem = topRatedItemList[position]

        with(holder.binding) {
            holder.itemView.tag = movieItem
            title.text = movieItem.title
            year.text = movieItem.release_date

            Glide.with(imageMovie.context)
                .load(secureBaseUrl + movieItem.poster_path)
                .into(imageMovie)
        }
    }

    override fun getItemCount(): Int = topRatedItemList.size

    class TopRatedViewHolder(
        val binding: MovieItemBinding
    ) : RecyclerView.ViewHolder(binding.root)
}