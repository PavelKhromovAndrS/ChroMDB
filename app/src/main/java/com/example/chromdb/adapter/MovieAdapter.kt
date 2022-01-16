package com.example.chromdb.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chromdb.databinding.MovieItemBinding
import com.example.chromdb.model.entities.MovieItem
import com.bumptech.glide.Glide
import com.example.chromdb.R

class MovieAdapter() : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    var movieList = emptyList<MovieItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        val glide: Glide = Glide.get(parent.context)

        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieItemBinding.inflate(inflater, parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        val movie = movieList[position]
        with(holder.binding) {
            title.text = movie.title
            year.text = movie.year.toString()

            Glide.with(imageMovie.context)
                .load(movie.image)
                .placeholder(R.drawable.ic_baseline_movie_24)
                .into(imageMovie)
        }
    }

    override fun getItemCount(): Int = movieList.size

    class MovieViewHolder(
        val binding: MovieItemBinding
    ) : RecyclerView.ViewHolder(binding.root)
}