package com.onoh.moviecataloeguerepositoryarch.ui.favorite.tvshow

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
import com.onoh.moviecataloeguerepositoryarch.data.local.entity.TvShowEntity
import com.onoh.moviecataloeguerepositoryarch.ui.detail.DetailMvActivity
import com.onoh.moviecataloeguerepositoryarch.ui.detail.DetailTvActivity
import com.onoh.moviecataloeguerepositoryarch.ui.favorite.movie.FavMovieAdapter
import kotlinx.android.synthetic.main.items_movies.view.*
import kotlinx.android.synthetic.main.items_tv_shows.view.*

class FavTvAdapter() : PagedListAdapter<TvShowEntity, FavTvAdapter.FavTvViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntity>() {
            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem.tvShowId == newItem.tvShowId
            }
            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavTvAdapter.FavTvViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_tv_shows, parent, false)
        return FavTvViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavTvAdapter.FavTvViewHolder, position: Int) {
        val tvFav = getItem(position)
        if (tvFav != null) {
            holder.bind(tvFav)
        }
    }

    fun getSwipedData(swipedPosition: Int): TvShowEntity? = getItem(swipedPosition)

    inner class FavTvViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(tv: TvShowEntity) {
            with(itemView) {
                tv_item_title_tv.text = tv.title
                tv_item_rating_tv.text = tv.rating.toString()
                setOnClickListener {
                    val intent = Intent(context, DetailTvActivity::class.java).apply {
                        putExtra(DetailTvActivity.EXTRA_TV, tv.tvShowId)
                    }
                    context.startActivity(intent)
                }
                Glide.with(context)
                    .load("https://image.tmdb.org/t/p/w500/"+tv.posterImage)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_error)
                            .error(R.drawable.ic_error))
                    .into(img_poster_item_tv)
            }
        }
    }


}