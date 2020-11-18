package com.onoh.moviecataloeguerepositoryarch.ui.detail

import android.graphics.Movie
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.onoh.moviecataloeguerepositoryarch.data.AppRepository
import com.onoh.moviecataloeguerepositoryarch.data.local.entity.CastMovieEntity
import com.onoh.moviecataloeguerepositoryarch.data.local.entity.CastTvEntity
import com.onoh.moviecataloeguerepositoryarch.data.local.entity.MovieEntity
import com.onoh.moviecataloeguerepositoryarch.data.local.entity.TvShowEntity
import com.onoh.moviecataloeguerepositoryarch.utils.DataDummy
import com.onoh.moviecataloeguerepositoryarch.vo.Resource
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

////    -Memastikan data movie tidak null
////    -Memastikan jumlah data movie sesua dengan yang diharapkan
////    -Memastikan data tvshow tidak null
////    -Memastikan jumlah data tvshow sesua dengan yang diharapkan



    private lateinit var viewModel: DetailViewModel
    private val dummyMovie = DataDummy.generateDummyMovies()[0]
    private val movieId = dummyMovie.movieId

    private val dummyTv = DataDummy.generateDummyDsvTv()[0]
    private val tvId = dummyTv.tvId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var appRepository: AppRepository

    @Mock
    private lateinit var movieObserver: Observer<Resource<MovieEntity>>
    @Mock
    private lateinit var tvShowObserver: Observer<Resource<TvShowEntity>>


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = DetailViewModel(appRepository)
        movieId?.let { viewModel.setSelectedMovie(it) }
        tvId?.let { viewModel.setSelectedtvShow(it) }
    }


//
    @Test
    fun getDetailMovie() {
    val dummyMovie = Resource.success(DataDummy.generateDummyMovies()[0])
    val movie = MutableLiveData<Resource<MovieEntity>>()
    movie.value = dummyMovie

    `when`(movieId?.let { appRepository.getDetailMovie(it) }).thenReturn(movie)

    viewModel.detailMovie.observeForever(movieObserver)
    verify(movieObserver).onChanged(dummyMovie)
}

    @Test
    fun getDetailTv() {
        val tv = MutableLiveData<Resource<TvShowEntity>>()
        val dummyTv = Resource.success(DataDummy.generateDummyTvShows()[0])
        tv.value = dummyTv
        `when`(tvId?.let { appRepository.getDetailTvshow(it) }).thenReturn(tv)

        viewModel.detailTvShow.observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(dummyTv)
    }


}