package com.onoh.moviecataloeguerepositoryarch.ui.favorite.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.onoh.moviecataloeguerepositoryarch.data.AppRepository
import com.onoh.moviecataloeguerepositoryarch.data.local.entity.MovieEntity

class FavMovieViewModel (private val appRepository: AppRepository) : ViewModel() {
    fun getFavMovies(): LiveData<PagedList<MovieEntity>> = appRepository.getFavoriteMovie()

    fun setMvFavorite(movieEntity: MovieEntity) {
        val newState = !movieEntity.favorite
        appRepository.setFavoriteMovie(movieEntity, newState)
    }
}