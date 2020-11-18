package com.onoh.moviecataloeguerepositoryarch.ui.dashboard.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.onoh.moviecataloeguerepositoryarch.data.local.entity.TvShowEntity
import com.onoh.moviecataloeguerepositoryarch.vo.Resource
import com.onoh.moviecataloeguerepositoryarch.data.AppRepository


class TvshowViewModel(private val appRepository: AppRepository): ViewModel() {
    fun getTvShows():LiveData<Resource<PagedList<TvShowEntity>>> = appRepository.getAllTvshows()
}