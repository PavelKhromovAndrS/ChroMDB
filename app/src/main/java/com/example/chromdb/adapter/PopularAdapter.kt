package com.example.chromdb.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chromdb.databinding.MovieItemBinding
import com.example.chromdb.model.entities.rest_entities.popular.PopularMovieItem

interface PopularItemActionListener {
    fun onPopularItemDetails(movieItem: PopularMovieItem)
}

class PopularAdapter(private val popularItemActionListener: PopularItemActionListener) :
    RecyclerView.Adapter<PopularAdapter.PopularViewHolder>(), View.OnClickListener {

    var secureBaseUrl: String = ""

    var popularItemList = emptyList<PopularMovieItem>()

    override fun onClick(v: View) {
        val movie = v.tag as PopularMovieItem
        popularItemActionListener.onPopularItemDetails(movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.root.setOnClickListener(this)
        return PopularViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        val movieItem = popularItemList[position]
        with(holder.binding) {
            holder.itemView.tag = movieItem
            title.text = movieItem.title
            year.text = movieItem.release_date

            Glide.with(imageMovie.context)
                .load(secureBaseUrl + movieItem.poster_path)
                .into(imageMovie)
        }
    }

    override fun getItemCount(): Int = popularItemList.size

    class PopularViewHolder(
        val binding: MovieItemBinding
    ) : RecyclerView.ViewHolder(binding.root)


}