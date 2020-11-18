package com.onoh.moviecataloeguerepositoryarch.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.onoh.moviecataloeguerepositoryarch.R
import kotlinx.android.synthetic.main.fragment_favorites.*

class FavoriteFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorites,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sectionFav = SectionsPagerFavAdapter(requireContext(),childFragmentManager)
        view_pager_fav.adapter = sectionFav
        tabs_fav.setupWithViewPager(view_pager_fav)
    }
}
