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
import com.onoh.moviecataloeguerepositoryarch.data.local.entity.DiscoveryTvEntity
import com.onoh.moviecataloeguerepositoryarch.ui.detail.DetailTvActivity
import kotlinx.android.synthetic.main.item_poster_potrait.view.*

class DiscoveryTvAdapter :PagedListAdapter<DiscoveryTvEntity,DiscoveryTvAdapter.TvViewHolder>(DIFF_CALLBACK) {

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DiscoveryTvEntity>() {
            override fun areItemsTheSame(oldItem: DiscoveryTvEntity, newItem: DiscoveryTvEntity): Boolean {
                return oldItem.tvId == newItem.tvId
            }
            override fun areContentsTheSame(oldItem: DiscoveryTvEntity, newItem: DiscoveryTvEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_poster_potrait, parent, false)
        return TvViewHolder(view)
    }

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) {
        val tv = getItem(position)
        if(tv != null){
            holder.bind(tv)
        }
    }

    class TvViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(tv: DiscoveryTvEntity) {
            with(itemView) {
                tv_title_dsv_tv.text = tv.title
                tv_desc_dsv_tv.text = tv.overview
                setOnClickListener {
                    val intent = Intent(context, DetailTvActivity::class.java).apply {
                        putExtra(DetailTvActivity.EXTRA_TV, tv.tvId)
                    }
                    context.startActivity(intent)
                }
                Glide.with(context)
                    .load("https://image.tmdb.org/t/p/w500/"+tv.posterImage)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_error)
                            .error(R.drawable.ic_error))
                    .into(img_poster_dsv_tv)
            }
        }
    }
}