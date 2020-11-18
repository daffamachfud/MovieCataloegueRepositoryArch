package com.onoh.moviecataloeguerepositoryarch.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import org.junit.Test
import org.mockito.Mockito.mock
import com.nhaarman.mockitokotlin2.verify
import com.onoh.academy.utils.LiveDataTestUtil
import com.onoh.academy.utils.PagedListUtil
import com.onoh.moviecataloeguerepositoryarch.data.local.LocalDataSource
import com.onoh.moviecataloeguerepositoryarch.data.local.entity.*
import com.onoh.moviecataloeguerepositoryarch.data.remote.RemoteDataSource
import com.onoh.moviecataloeguerepositoryarch.utils.AppExecutors
import com.onoh.moviecataloeguerepositoryarch.utils.DataDummy
import com.onoh.moviecataloeguerepositoryarch.vo.Resource
import org.junit.Rule
import org.junit.Assert.*
import org.mockito.Mockito
import org.mockito.Mockito.`when`

class AppRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)

    private val appRepository = FakeAppRepository(remote,local,appExecutors)

    private val movieResponses = DataDummy.generateDummyMovies()
    private val movieId = movieResponses[0].movieId
    private val detailMovie = DataDummy.generateDummyDetailMovie(movieId!!)

    private val tvResponse = DataDummy.generateDummyTvShows()
    private val tvId = tvResponse[0].tvShowId
    private val detailTvShow = DataDummy.generateDummyDetailTv(tvId!!)


    private val dsvMovieResponse = DataDummy.generateDummyDsvMv()
    private val dsvTvResponse = DataDummy.generateDummyDsvTv()
    private val trendResponse = DataDummy.generateDummyTrendResponse()

    @Test
    fun getAllMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        Mockito.`when`(local.getAllMovies()).thenReturn(dataSourceFactory)
        appRepository.getAllMovies()

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()))
        verify(local).getAllMovies()
        assertNotNull(movieEntities.data)
        assertEquals(movieResponses.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getAllTvshows() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        Mockito.`when`(local.getAllTvShows()).thenReturn(dataSourceFactory)
        appRepository.getAllTvshows()

        val tvEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTvShows()))
        verify(local).getAllTvShows()
        assertNotNull(tvEntities.data)
        assertEquals(tvResponse.size.toLong(), tvEntities.data?.size?.toLong())
    }

    @Test
    fun getAllDiscoveryMv(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int,DiscoveryMovieEntity>
        Mockito.`when`(local.getDsvMovies()).thenReturn(dataSourceFactory)
        appRepository.getDsvMovies()

        val dsvMvEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyDsvMv()))
        verify(local).getDsvMovies()
        assertNotNull(dsvMvEntities.data)
        assertEquals(dsvMovieResponse.size.toLong(),dsvMvEntities.data?.size?.toLong())
    }

    @Test
    fun getAllDiscoveryTv(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int,DiscoveryTvEntity>
        Mockito.`when`(local.getDsvTv()).thenReturn(dataSourceFactory)
        appRepository.getDsvTv()

        val dsvTvEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyDsvTv()))
        verify(local).getDsvTv()
        assertNotNull(dsvTvEntities.data)
        assertEquals(dsvTvResponse.size.toLong(),dsvTvEntities.data?.size?.toLong())
    }

    @Test
    fun getAllTrend(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int,TrendEntity>
        Mockito.`when`(local.getTrend()).thenReturn(dataSourceFactory)
        appRepository.getTrend()

        val trendEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTrendResponse()))
        verify(local).getTrend()
        assertNotNull(trendEntities.data)
        assertEquals(trendResponse.size.toLong(),trendEntities.data?.size?.toLong())
    }

    @Test
    fun getAllFavoriteMovie(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int,MovieEntity>
        Mockito.`when`(local.getFavoriteMovies()).thenReturn(dataSourceFactory)
        appRepository.getFavoriteMovie()

        val favEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()))
        verify(local).getFavoriteMovies()
        assertNotNull(favEntities.data)
        assertEquals(movieResponses.size.toLong(), favEntities.data?.size?.toLong())
    }

    @Test
    fun getAllFavoriteTvshow(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int,TvShowEntity>
        Mockito.`when`(local.getFavoriteTvshows()).thenReturn(dataSourceFactory)
        appRepository.getFavoriteTv()

        val favEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTvShows()))
        verify(local).getFavoriteTvshows()
        assertNotNull(favEntities.data)
        assertEquals(tvResponse.size.toLong(), favEntities.data?.size?.toLong())
    }

    @Test
    fun getDetailMovie(){
        val dummyTvEntity = MutableLiveData<TvShowEntity>()
        dummyTvEntity.value = DataDummy.generateDummyTvShows()[0]
        `when`< LiveData <TvShowEntity>>(tvId?.let { local.getLocalDetailTvshow(it) }).thenReturn(dummyTvEntity)

        if(tvId != null){
            val tvEntities = LiveDataTestUtil.getValue(appRepository.getDetailTvshow(tvId))
            verify(local).getLocalDetailTvshow(tvId)
            assertNotNull(tvEntities.data)
            assertEquals(detailTvShow.tvShowId,tvEntities.data?.tvShowId)
        }
    }





}