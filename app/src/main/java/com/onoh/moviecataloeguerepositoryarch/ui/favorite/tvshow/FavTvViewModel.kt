package com.onoh.moviecataloeguerepositoryarch.ui.favorite.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.onoh.moviecataloeguerepositoryarch.data.AppRepository
import com.onoh.moviecataloeguerepositoryarch.data.local.entity.CastTvEntity
import com.onoh.moviecataloeguerepositoryarch.data.local.entity.MovieEntity
import com.onoh.moviecataloeguerepositoryarch.data.local.entity.TvShowEntity

class FavTvViewModel (private val appRepository: AppRepository) : ViewModel() {
    fun getFavTv(): LiveData<PagedList<TvShowEntity>> = appRepository.getFavoriteTv()

    fun setFavTv(tvEntity: TvShowEntity) {
        val newState = !tvEntity.favorite
        appRepository.setFavoriteTvShow(tvEntity, newState)
    }
}