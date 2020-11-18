package com.onoh.moviecataloeguerepositoryarch.utils

import com.onoh.moviecataloeguerepositoryarch.BuildConfig
import com.onoh.moviecataloeguerepositoryarch.data.local.entity.DiscoveryMovieEntity
import com.onoh.moviecataloeguerepositoryarch.data.local.entity.DiscoveryTvEntity
import com.onoh.moviecataloeguerepositoryarch.data.local.entity.MovieEntity
import com.onoh.moviecataloeguerepositoryarch.data.local.entity.TvShowEntity
import com.onoh.moviecataloeguerepositoryarch.data.remote.response.*
import com.onoh.moviecataloeguerepositoryarch.network.RetrofitClient
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response

object DataDummy {

    fun generateDummyMoviesResponse():List<MovieEntity>{
        val movies = ArrayList<MovieEntity>()
        RetrofitClient.api().getAllMovie(BuildConfig.API_KEY).enqueue(object :Callback<MovieResponse>{
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
               if(response.isSuccessful){
                   val data =response.body()?.results
                   if (data != null) {
                       for(d in data){
                            movies.add(MovieEntity(d?.id,d?.originalTitle,d?.popularity,d?.overview,d?.releaseDate,d?.posterPath,d?.backdropPath,false))
                       }
                   }
               }
            }
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {}
        })
        return movies
    }

    fun generateDummyTvShowsResponse():List<TvShowsResult>{
        val tvShows = ArrayList<TvShowsResult>()
        RetrofitClient.api().getAllTvShow(BuildConfig.API_KEY).enqueue(object :Callback<TvShowResponse>{
            override fun onResponse(call: Call<TvShowResponse>, response: Response<TvShowResponse>) {
                if(response.isSuccessful){
                    val data = response.body()?.results
                    if(data!=null){
                        for(d in data){
                            tvShows.add(TvShowsResult(d?.firstAirDate,d?.overview,d?.originalLanguage,d?.genreIds,d?.posterPath,d?.originCountry,d?.backdropPath,d?.originalName,d?.popularity,d?.voteAverage,d?.originalName,d?.id,d?.voteCount))
                        }
                    }
                }
            }
            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) { }
        })
        return tvShows
    }

    fun generateDummyTrendResponse():List<TrendResult>{
        val trend = ArrayList<TrendResult>()
        RetrofitClient.api().getTrend(BuildConfig.API_KEY).enqueue(object :Callback<TrendResponse>{
            override fun onResponse(call: Call<TrendResponse>, response: Response<TrendResponse>) {
                if(response.isSuccessful){
                    val data = response.body()?.results
                    if(data!=null){
                        for(d in data){
                            trend.add(TrendResult(d?.firstAirDate,d?.overview,d?.originalLanguage,d?.genreIds,d?.posterPath,d?.originCountry,d?.backdropPath,d?.mediaType,
                            d?.originalName,d?.voteAverage,d?.popularity,d?.name,d?.id,d?.voteCount,d?.originalTitle,d?.video,d?.title,d?.releaseDate,d?.adult))
                        }
                    }
                }
            }
            override fun onFailure(call: Call<TrendResponse>, t: Throwable) {}
        })
        return trend
    }

    fun generateDummyDsvMovieResponse():List<MoviesResult>{
        val dsvMovie = ArrayList<MoviesResult>()
        RetrofitClient.api().getDiscoverMovie(BuildConfig.API_KEY).enqueue(object :Callback<MovieResponse>{
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if(response.isSuccessful){
                    val data = response.body()?.results
                    if(data!=null){
                        for(d in data){
                            dsvMovie.add(
                                MoviesResult(d?.overview,d?.originalLanguage,d?.originalTitle,d?.video,d?.originalTitle,d?.genreIds,
                            d?.posterPath,d?.backdropPath,d?.releaseDate,d?.popularity,d?.voteAverage,d?.id,d?.adult,d?.voteCount))
                        }
                    }
                }
            }
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {}
        })
        return dsvMovie
    }

    fun generateDummyDsvTvResponse():List<TvShowsResult>{
        val dsvTv = ArrayList<TvShowsResult>()
        RetrofitClient.api().getDiscoverTv(BuildConfig.API_KEY).enqueue(object :Callback<TvShowResponse>{
            override fun onResponse(call: Call<TvShowResponse>, response: Response<TvShowResponse>) {
                if(response.isSuccessful){
                    val data = response.body()?.results
                    if(data!=null){
                        for(d in data){
                            dsvTv.add(
                                TvShowsResult(d?.firstAirDate,d?.overview,d?.originalLanguage,d?.genreIds,d?.posterPath,d?.originCountry,
                                d?.backdropPath,d?.originalName,d?.popularity,d?.voteAverage,d?.originalName,d?.id,d?.voteCount))
                        }
                    }
                }
            }
            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {}
        })
        return dsvTv
    }

    fun generateDummyMovies():List<MovieEntity>{
        val movies = ArrayList<MovieEntity>()

        movies.add(
            MovieEntity(
                671039,
                "Bronx",
                6.1,
                "Caught in the crosshairs of police corruption and Marseille’s warring gangs, a loyal cop must protect his squad by taking matters into his own hands."
                ,"30 October 2020",
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/9HT9982bzgN5on1sLRmc1GMn6ZC.jpg","-",false)
        )

        movies.add(
            MovieEntity(
                2,
                "Alita:Battle Angel",
                7.5,
                "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past."
                ,"2 February 2019",
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/xRWht48C2V8XNfzvPehyClOvDni.jpg","-",false)
        )

        movies.add(
            MovieEntity(
                3,
                "Aquaman",
                7.0,
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne."
                ,"21 December 2018",
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/5Kg76ldv7VxeX9YlcQXiowHgdX6.jpg","-",false)
        )

        movies.add(
            MovieEntity(
                4,
                "Bohemian Rhapsody",
                8.5,
                "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess."
                ,"24 October 2018",
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/lHu1wtNaczFPGFDTrjCSzeLPTKN.jpg","-",false)
        )

        movies.add(
            MovieEntity(
                5,
                "Creed II",
                6.0,
                "Between personal obligations and training for his next big fight against an opponent with ties to his family's past, Adonis Creed is up against the challenge of his life."
                ,"21 November 2018",
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/v3QyboWRoA4O9RbcsqH8tJMe8EB.jpg","-",false)
        )

        movies.add(
            MovieEntity(
                6,
                "Glass",
                6.0,
                "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men."
                ,"16 January 2019",
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/svIDTNUoajS8dLEo7EosxvyAsgJ.jpg","-",false)
        )

        movies.add(
            MovieEntity(
                7,
                "Ip Man 4:The Finale",
                6.0,
                "Following the death of his wife, Ip Man travels to San Francisco to ease tensions between the local kung fu masters and his star student, Bruce Lee, while searching for a better future for his son."
                ,"19 December 2019",
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/swmjF0CQc59K3jdUFzOirXESeGN.jpg","-",false)
        )

        movies.add(
            MovieEntity(
                7,
                "Ip Man 4:The Finale",
                6.0,
                "Following the death of his wife, Ip Man travels to San Francisco to ease tensions between the local kung fu masters and his star student, Bruce Lee, while searching for a better future for his son."
                ,"19 December 2019",
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/swmjF0CQc59K3jdUFzOirXESeGN.jpg","-",false)
        )

        movies.add(
            MovieEntity(
                7,
                "Ip Man 4:The Finale",
                6.0,
                "Following the death of his wife, Ip Man travels to San Francisco to ease tensions between the local kung fu masters and his star student, Bruce Lee, while searching for a better future for his son."
                ,"19 December 2019",
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/swmjF0CQc59K3jdUFzOirXESeGN.jpg","-",false)
        )

        movies.add(
            MovieEntity(
                7,
                "Ip Man 4:The Finale",
                6.0,
                "Following the death of his wife, Ip Man travels to San Francisco to ease tensions between the local kung fu masters and his star student, Bruce Lee, while searching for a better future for his son."
                ,"19 December 2019",
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/swmjF0CQc59K3jdUFzOirXESeGN.jpg","-",false)
        )

        return movies
    }

    fun generateDummyTvShows():List<TvShowEntity>{
        val tvShows = ArrayList<TvShowEntity>()

        tvShows.add(
            TvShowEntity(
                82856,
            "The Mandalorian",
            8.4,
            "After the fall of the Galactic Empire, lawlessness has spread throughout the galaxy. A lone gunfighter makes his way through the outer reaches, earning his keep as a bounty hunter.",
                12,
                1,
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/sWgBv7LV2PRoQgkxwlibdGXKz1S.jpg","-",false)
        )

        tvShows.add(
            TvShowEntity(
                1,
                "Game Of Thrones",
                6.0,
                "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
                6,
                8,
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg","-",false)
        )

        tvShows.add(
            TvShowEntity(
                1,
                "Game Of Thrones",
                6.0,
                "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
                6,
                8,
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg","-",false)
        )

        tvShows.add(
            TvShowEntity(
                1,
                "Game Of Thrones",
                6.0,
                "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
                6,
                8,
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg","-",false)
        )

        tvShows.add(
            TvShowEntity(
                1,
                "Game Of Thrones",
                6.0,
                "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
                6,
                8,
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg","-",false)
        )

        tvShows.add(
            TvShowEntity(
                1,
                "Game Of Thrones",
                6.0,
                "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
                6,
                8,
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg","-",false)
        )

        tvShows.add(
            TvShowEntity(
                1,
                "Game Of Thrones",
                6.0,
                "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
                6,
                8,
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg","-",false)
        )

        tvShows.add(
            TvShowEntity(
                1,
                "Game Of Thrones",
                6.0,
                "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
                6,
                8,
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg","-",false)
        )

        tvShows.add(
            TvShowEntity(
                1,
                "Game Of Thrones",
                6.0,
                "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
                6,
                8,
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg","-",false)
        )

        tvShows.add(
            TvShowEntity(
                1,
                "Game Of Thrones",
                6.0,
                "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
                6,
                8,
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg","-",false)
        )

        return tvShows
    }

    fun generateDummyDsvMv():List<DiscoveryMovieEntity>{
        val dsvMv = ArrayList<DiscoveryMovieEntity>()

        dsvMv.add(
            DiscoveryMovieEntity(
                671039,
                "Bronx",
                6.1,
                "Caught in the crosshairs of police corruption and Marseille’s warring gangs, a loyal cop must protect his squad by taking matters into his own hands."
                ,"30 October 2020",
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/9HT9982bzgN5on1sLRmc1GMn6ZC.jpg",false)
        )

        return dsvMv
    }

    fun generateDummyDsvTv():List<DiscoveryTvEntity>{
        val dsvTv = ArrayList<DiscoveryTvEntity>()

        dsvTv.add(
            DiscoveryTvEntity(
                82856,
                "The Mandalorian",
                8.4,
                "After the fall of the Galactic Empire, lawlessness has spread throughout the galaxy. A lone gunfighter makes his way through the outer reaches, earning his keep as a bounty hunter.",
                "12 November 2019","\"https://image.tmdb.org/t/p/w600_and_h900_bestv2/sWgBv7LV2PRoQgkxwlibdGXKz1S.jpg\"",
                false)
        )
        return dsvTv
    }

    fun generateDummyDetailMovie(movieId:Int):MovieEntity = MovieEntity(movieId,"Dummy",null,"","","","",false)
    fun generateDummyDetailTv(tvId:Int):TvShowEntity = TvShowEntity(tvId,"Dummy",null,null,null,null,null,null,false)



}