package com.onoh.moviecataloeguerepositoryarch.ui.dashboard.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.onoh.moviecataloeguerepositoryarch.data.local.entity.MovieEntity
import com.onoh.moviecataloeguerepositoryarch.vo.Resource
import com.onoh.moviecataloeguerepositoryarch.data.AppRepository


class MovieViewModel(private val appRepository: AppRepository) : ViewModel() {
    fun getMovies():LiveData<Resource<PagedList<MovieEntity>>> = appRepository.getAllMovies()
}