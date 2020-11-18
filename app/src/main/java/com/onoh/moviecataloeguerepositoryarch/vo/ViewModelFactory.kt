package com.onoh.moviecataloeguerepositoryarch.vo

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.onoh.moviecataloeguerepositoryarch.data.AppRepository
import com.onoh.moviecataloeguerepositoryarch.di.Injection
import com.onoh.moviecataloeguerepositoryarch.ui.dashboard.movie.MovieViewModel
import com.onoh.moviecataloeguerepositoryarch.ui.dashboard.tvshow.TvshowViewModel
import com.onoh.moviecataloeguerepositoryarch.ui.detail.DetailViewModel
import com.onoh.moviecataloeguerepositoryarch.ui.favorite.movie.FavMovieViewModel
import com.onoh.moviecataloeguerepositoryarch.ui.favorite.tvshow.FavTvViewModel
import com.onoh.moviecataloeguerepositoryarch.ui.home.HomeViewModel

class ViewModelFactory  private constructor(private val mAppRepository: AppRepository) : ViewModelProvider.NewInstanceFactory() {
    companion object{
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(MovieViewModel::class.java)->{
                MovieViewModel(mAppRepository) as T
            }
            modelClass.isAssignableFrom(TvshowViewModel::class.java)->{
                TvshowViewModel(mAppRepository) as T
            }
            modelClass.isAssignableFrom(HomeViewModel::class.java)->{
                HomeViewModel(mAppRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java)->{
                DetailViewModel(mAppRepository) as T
            }
            modelClass.isAssignableFrom(FavMovieViewModel::class.java)->{
                FavMovieViewModel(mAppRepository) as T
            }
            modelClass.isAssignableFrom(FavTvViewModel::class.java)->{
                FavTvViewModel(mAppRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}