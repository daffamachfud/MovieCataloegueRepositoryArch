package com.onoh.moviecataloeguerepositoryarch.ui.favorite

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.onoh.moviecataloeguerepositoryarch.R
import com.onoh.moviecataloeguerepositoryarch.ui.dashboard.movie.MovieFragment
import com.onoh.moviecataloeguerepositoryarch.ui.dashboard.tvshow.TvshowFragment
import com.onoh.moviecataloeguerepositoryarch.ui.favorite.movie.FavMovieFragment
import com.onoh.moviecataloeguerepositoryarch.ui.favorite.tvshow.FavTvshowFragment


class SectionsPagerFavAdapter (private val mContext: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.movie,R.string.tvshow)
    }

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> FavMovieFragment()
            1 -> FavTvshowFragment()
            else -> Fragment()
        }

    override fun getPageTitle(position: Int): CharSequence? =
        mContext.resources.getString(TAB_TITLES[position])

    override fun getCount(): Int = TAB_TITLES.size
}