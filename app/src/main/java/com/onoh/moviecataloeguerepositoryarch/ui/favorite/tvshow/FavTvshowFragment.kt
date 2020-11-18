package com.onoh.moviecataloeguerepositoryarch.ui.favorite.tvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.onoh.moviecataloeguerepositoryarch.R
import com.onoh.moviecataloeguerepositoryarch.vo.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_fav_movie.*
import kotlinx.android.synthetic.main.fragment_fav_movie.progress_bar
import kotlinx.android.synthetic.main.fragment_fav_tvshow.*

class FavTvshowFragment : Fragment() {

    private lateinit var viewModel: FavTvViewModel
    private lateinit var tvAdapter: FavTvAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_fav_tvshow, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        itemTouchHelper.attachToRecyclerView(rv_fav_movies)
        if(activity != null){
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this,factory)[FavTvViewModel::class.java]

            tvAdapter = FavTvAdapter()
            progress_bar.visibility = View.VISIBLE
            viewModel.getFavTv().observe(viewLifecycleOwner, {
                progress_bar.visibility = View.GONE
                tvAdapter.submitList(it)
            })

            with(rv_fav_tvs){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvAdapter
            }
        }
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int = makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean = true
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val tvEntity = tvAdapter.getSwipedData(swipedPosition)

                tvEntity?.let { viewModel.setFavTv(it) }

                val snackbar = Snackbar.make(view as View, R.string.message_undo, Snackbar.LENGTH_LONG)
                snackbar.setAction(R.string.message_ok) {
                    tvEntity?.let { viewModel.setFavTv(it) }
                }
                snackbar.show()
            }
        }
    })

}
