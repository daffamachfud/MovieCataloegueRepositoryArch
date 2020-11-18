package com.onoh.moviecataloeguerepositoryarch.ui.favorite.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.onoh.moviecataloeguerepositoryarch.R
import com.onoh.moviecataloeguerepositoryarch.data.local.entity.MovieEntity
import com.onoh.moviecataloeguerepositoryarch.ui.detail.DetailMvActivity
import kotlinx.android.synthetic.main.items_movies.view.*

class FavMovieAdapter() : PagedListAdapter<MovieEntity, FavMovieAdapter.FavMvViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.movieId == newItem.movieId
            }
            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavMvViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_movies, parent, false)
        return FavMvViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavMvViewHolder, position: Int) {
        val movieFav = getItem(position)
        if (movieFav != null) {
            holder.bind(movieFav)
        }
    }

    fun getSwipedData(swipedPosition: Int): MovieEntity? = getItem(swipedPosition)

    inner class FavMvViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: MovieEntity) {
            with(itemView) {
                tv_item_title_mv.text = movie.title
                tv_item_rating_mv.text = movie.rating.toString()
                tv_item_date_release_mv.text = resources.getString(R.string.release_date,movie.dateRelease)
                setOnClickListener {
                    val intent = Intent(context, DetailMvActivity::class.java).apply {
                        putExtra(DetailMvActivity.EXTRA_MOVIE, movie.movieId)
                    }
                    context.startActivity(intent)
                }
                Glide.with(context)
                    .load("https://image.tmdb.org/t/p/w500/"+movie.posterImage)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_error)
                            .error(R.drawable.ic_error))
                    .into(img_poster_mv)
            }
        }
    }


}