package com.onoh.moviecataloeguerepositoryarch.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.onoh.moviecataloeguerepositoryarch.data.AppRepository
import com.onoh.moviecataloeguerepositoryarch.data.local.entity.DiscoveryMovieEntity
import com.onoh.moviecataloeguerepositoryarch.data.local.entity.DiscoveryTvEntity
import com.onoh.moviecataloeguerepositoryarch.data.local.entity.MovieEntity
import com.onoh.moviecataloeguerepositoryarch.data.local.entity.TrendEntity
import com.onoh.moviecataloeguerepositoryarch.vo.Resource

class HomeViewModel(private val appRepository: AppRepository) : ViewModel() {
    fun getDsvMovies():LiveData<Resource<PagedList<DiscoveryMovieEntity>>> = appRepository.getDsvMovies()
    fun getDsvTv():LiveData<Resource<PagedList<DiscoveryTvEntity>>> = appRepository.getDsvTv()
    fun getTrending():LiveData<Resource<PagedList<TrendEntity>>> = appRepository.getTrend()
}