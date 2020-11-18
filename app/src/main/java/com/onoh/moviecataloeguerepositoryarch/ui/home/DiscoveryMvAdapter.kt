package com.onoh.moviecataloeguerepositoryarch.ui.home

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
import com.onoh.moviecataloeguerepositoryarch.data.local.entity.DiscoveryMovieEntity
import com.onoh.moviecataloeguerepositoryarch.ui.detail.DetailMvActivity
import kotlinx.android.synthetic.main.item_poster_landscape.view.*

class DiscoveryMvAdapter : PagedListAdapter<DiscoveryMovieEntity,DiscoveryMvAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DiscoveryMovieEntity>() {
            override fun areItemsTheSame(oldItem: DiscoveryMovieEntity, newItem: DiscoveryMovieEntity): Boolean {
                return oldItem.movieId == newItem.movieId
            }
            override fun areContentsTheSame(oldItem: DiscoveryMovieEntity, newItem: DiscoveryMovieEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_poster_landscape, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        if(movie != null){
            holder.bind(movie)
        }
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(movie: DiscoveryMovieEntity) {
            with(itemView) {

                tv_rating_dsv_mv.text = movie.rating.toString()
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
                    .into(img_poster_dsv_mv)
            }
        }
    }
}