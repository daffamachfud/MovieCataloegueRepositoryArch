package com.onoh.moviecataloeguerepositoryarch.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.onoh.moviecataloeguerepositoryarch.R
import com.onoh.moviecataloeguerepositoryarch.data.local.entity.CastMovieEntity
import com.onoh.moviecataloeguerepositoryarch.data.local.entity.CastTvEntity
import kotlinx.android.synthetic.main.item_cast.view.*
import java.util.ArrayList

class CastAdapter (type:String):RecyclerView.Adapter<CastAdapter.CastViewHolder>() {
    private var listCastMv = ArrayList<CastMovieEntity>()
    private var listCastTv = ArrayList<CastTvEntity>()
    private val typeAdapter = type

    fun setMvCast(castMv: List<CastMovieEntity>?) {
        if (castMv == null) return
        listCastMv.clear()
        listCastMv.addAll(castMv)
    }

    fun setTvCast(castTv: List<CastTvEntity>?) {
        if (castTv == null) return
        listCastTv.clear()
        listCastTv.addAll(castTv)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cast, parent, false)
        return CastViewHolder(view)
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        if(typeAdapter == "movie"){
            val castMv = listCastMv[position]
            holder.bindCastMv(castMv)
        }else{
            val tv = listCastTv[position]
            holder.bindCastTv(tv)
        }

    }

    override fun getItemCount(): Int {
        return if(typeAdapter == "movie"){
            listCastMv.size
        }else{
            listCastTv.size
        }
    }

    class CastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindCastMv(castMv: CastMovieEntity) {
            with(itemView) {
                tv_name_cast.text = castMv.name
                Glide.with(context)
                    .load("https://image.tmdb.org/t/p/w500/"+castMv.profilePath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_error)
                            .error(R.drawable.ic_error))
                    .into(img_profile_cast)
            }
        }

        fun bindCastTv(tv: CastTvEntity) {
            with(itemView) {
                tv_name_cast.text = tv.name
                Glide.with(context)
                    .load("https://image.tmdb.org/t/p/w500/"+tv.profilePath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_error)
                            .error(R.drawable.ic_error))
                    .into(img_profile_cast)
                }
            }
        }

    }