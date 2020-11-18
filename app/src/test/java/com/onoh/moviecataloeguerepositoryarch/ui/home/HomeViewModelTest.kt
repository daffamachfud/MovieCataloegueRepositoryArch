package com.onoh.moviecataloeguerepositoryarch.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.onoh.moviecataloeguerepositoryarch.data.AppRepository
import com.onoh.moviecataloeguerepositoryarch.data.local.entity.DiscoveryMovieEntity
import com.onoh.moviecataloeguerepositoryarch.data.local.entity.DiscoveryTvEntity
import com.onoh.moviecataloeguerepositoryarch.data.local.entity.MovieEntity
import com.onoh.moviecataloeguerepositoryarch.data.local.entity.TrendEntity
import com.onoh.moviecataloeguerepositoryarch.ui.dashboard.movie.MovieViewModel
import com.onoh.moviecataloeguerepositoryarch.vo.Resource
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest{

//    objective tes ke 2
//    Memanipulasi data ketika pemanggilan data discovery movie,tv dan trending di kelas repository.
//    Memastikan metode di kelas repository terpanggil.
//    Melakukan pengecekan data discovery movie,tv dan trending, apakah null atau tidak.
//    Melakukan pengecekan jumlah data discovery movie,tv dan trending apakah sudah sesuai atau belum

    private lateinit var viewModel: HomeViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var appRepository: AppRepository

    @Mock
    private lateinit var observerDsvMv: Observer<Resource<PagedList<DiscoveryMovieEntity>>>
    @Mock
    private lateinit var observerDsvTv: Observer<Resource<PagedList<DiscoveryTvEntity>>>
    @Mock
    private lateinit var observerTrend: Observer<Resource<PagedList<TrendEntity>>>

    @Mock
    private lateinit var pagedListDsvMv: PagedList<DiscoveryMovieEntity>

    @Mock
    private lateinit var pagedListDsvTv: PagedList<DiscoveryTvEntity>

    @Mock
    private lateinit var pagedListTrend: PagedList<TrendEntity>

    @Before
    fun setUp() {
        viewModel = HomeViewModel(appRepository)
    }

    @Test
    fun getDstMovies() {
        val dummyDstMovie = Resource.success(pagedListDsvMv)
        Mockito.`when`(dummyDstMovie.data?.size).thenReturn(20)
        val dstMovie = MutableLiveData<Resource<PagedList<DiscoveryMovieEntity>>>()
        dstMovie.value = dummyDstMovie

        Mockito.`when`(appRepository.getDsvMovies()).thenReturn(dstMovie)
        val dstMovieEntities = viewModel.getDsvMovies().value?.data
        Mockito.verify(appRepository).getDsvMovies()
        Assert.assertNotNull(dstMovieEntities)
        Assert.assertEquals(20, dstMovieEntities?.size)

        viewModel.getDsvMovies().observeForever(observerDsvMv)
        Mockito.verify(observerDsvMv).onChanged(dummyDstMovie)
    }

    @Test
    fun getDstTv() {
        val dummyDstTv = Resource.success(pagedListDsvTv)
        Mockito.`when`(dummyDstTv.data?.size).thenReturn(20)
        val dstTv = MutableLiveData<Resource<PagedList<DiscoveryTvEntity>>>()
        dstTv.value = dummyDstTv

        Mockito.`when`(appRepository.getDsvTv()).thenReturn(dstTv)
        val dstTvEntities = viewModel.getDsvTv().value?.data
        Mockito.verify(appRepository).getDsvTv()
        Assert.assertNotNull(dstTvEntities)
        Assert.assertEquals(20, dstTvEntities?.size)

        viewModel.getDsvTv().observeForever(observerDsvTv)
        Mockito.verify(observerDsvTv).onChanged(dummyDstTv)
    }

    @Test
    fun getTrend() {
        val dummyTrend = Resource.success(pagedListTrend)
        Mockito.`when`(dummyTrend.data?.size).thenReturn(20)
        val dstTrend = MutableLiveData<Resource<PagedList<TrendEntity>>>()
        dstTrend.value = dummyTrend

        Mockito.`when`(appRepository.getTrend()).thenReturn(dstTrend)
        val dstTrendEntities = viewModel.getTrending().value?.data
        Mockito.verify(appRepository).getTrend()
        Assert.assertNotNull(dstTrendEntities)
        Assert.assertEquals(20, dstTrendEntities?.size)

        viewModel.getTrending().observeForever(observerTrend)
        Mockito.verify(observerTrend).onChanged(dummyTrend)
    }

}

