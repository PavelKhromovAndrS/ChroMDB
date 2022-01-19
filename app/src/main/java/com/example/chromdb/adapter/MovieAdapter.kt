package com.example.chromdb.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chromdb.databinding.MovieItemBinding
import com.example.chromdb.model.entities.MovieItem
import com.bumptech.glide.Glide
import com.example.chromdb.R
interface UserActionListener {
    fun onMovieDetails(movieItem: MovieItem)
}

class MovieAdapter(
    private val userActionListener: UserActionListener
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(),View.OnClickListener {

    var movieList = emptyList<MovieItem>()

    override fun onClick(v: View) {
        val movie = v.tag as MovieItem
        userActionListener.onMovieDetails(movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieItemBinding.inflate(inflater, parent, false)
        binding.root.setOnClickListener(this)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        val movie = movieList[position]

        with(holder.binding) {
            holder.itemView.tag = movie
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