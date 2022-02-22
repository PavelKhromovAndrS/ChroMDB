package com.example.chromdb.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chromdb.databinding.MovieItemBinding
import com.example.chromdb.model.entities.rest_entities.top_rated.MovieItem
import com.example.chromdb.ui.main.SECURE_URL

interface ActionListener {
    fun onMovieDetails(movieItem: MovieItem)
}

class MovieAdapter(private val actionListener: ActionListener) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(), View.OnClickListener {

    var secureBaseUrl: String = SECURE_URL
    var itemList = emptyList<MovieItem>()

    override fun onClick(v: View) {
        val movie = v.tag as MovieItem
        actionListener.onMovieDetails(movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieItemBinding.inflate(inflater, parent, false)
        binding.root.setOnClickListener(this)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        val movieItem = itemList[position]

        with(holder.binding) {
            holder.itemView.tag = movieItem
            title.text = movieItem.title

            Glide.with(imageMovie.context)
                .load(secureBaseUrl + movieItem.poster_path)
                .into(imageMovie)
        }
    }

    override fun getItemCount(): Int = itemList.size

    class MovieViewHolder(
        val binding: MovieItemBinding
    ) : RecyclerView.ViewHolder(binding.root)
}