package com.onoh.moviecataloeguerepositoryarch.ui.dashboard.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.onoh.moviecataloeguerepositoryarch.data.AppRepository
import com.onoh.moviecataloeguerepositoryarch.data.local.entity.MovieEntity
import com.onoh.moviecataloeguerepositoryarch.data.local.entity.TvShowEntity
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
class TvshowViewModelTest {
    //    -obj
//    1.Data tvshow tidak null
//    2.Memastikan jumlah data tvshow sesuai dengan yang diharapkan

    //    objective tes ke 2
//    Memanipulasi data ketika pemanggilan data tvshow di kelas repository.
//    Memastikan metode di kelas repository terpanggil.
//    Melakukan pengecekan data tvshow apakah null atau tidak.
//    Melakukan pengecekan jumlah data tvshow apakah sudah sesuai atau belum

    private lateinit var viewModel: TvshowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var appRepository: AppRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<TvShowEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<TvShowEntity>

    @Before
    fun setUp() {
        viewModel = TvshowViewModel(appRepository)
    }

    @Test
    fun getTvShows() {
        val dummyTv = Resource.success(pagedList)
        Mockito.`when`(dummyTv.data?.size).thenReturn(20)
        val tvshows = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        tvshows.value = dummyTv

        Mockito.`when`(appRepository.getAllTvshows()).thenReturn(tvshows)
        val tvEntities = viewModel.getTvShows().value?.data
        Mockito.verify(appRepository).getAllTvshows()
        Assert.assertNotNull(tvEntities)
        Assert.assertEquals(20, tvEntities?.size)

        viewModel.getTvShows().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyTv)
    }
}