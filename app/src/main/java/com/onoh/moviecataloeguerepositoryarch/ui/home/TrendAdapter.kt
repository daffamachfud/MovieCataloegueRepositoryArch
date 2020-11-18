package com.onoh.moviecataloeguerepositoryarch.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.onoh.moviecataloeguerepositoryarch.R
import com.onoh.moviecataloeguerepositoryarch.data.local.entity.TrendEntity
import kotlinx.android.synthetic.main.item_trending.view.*

class TrendAdapter: PagedListAdapter<TrendEntity,TrendAdapter.TrendViewHolder>(DIFF_CALLBACK){

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TrendEntity>() {
            override fun areItemsTheSame(oldItem: TrendEntity, newItem: TrendEntity): Boolean {
                return oldItem.trendId == newItem.trendId
            }
            override fun areContentsTheSame(oldItem: TrendEntity, newItem: TrendEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_trending, parent, false)
        return TrendViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrendViewHolder, position: Int) {
        val trend = getItem(position)
        if(trend != null){
            holder.bind(trend)
        }
    }

    class TrendViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(trend: TrendEntity) {
            with(itemView) {
                if(trend.mediaType == "movie"){
                    tv_item_title_trend.text = trend.title.toString()
                }else{
                    tv_item_title_trend.text = trend.originalName.toString()
                }
                Glide.with(context)
                    .load("https://image.tmdb.org/t/p/w500/"+trend.posterImage)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_error)
                            .error(R.drawable.ic_error))
                    .into(img_poster_trend)
            }
        }
    }

}