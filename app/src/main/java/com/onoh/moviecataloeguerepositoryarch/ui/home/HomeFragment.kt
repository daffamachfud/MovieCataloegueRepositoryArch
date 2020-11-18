package com.onoh.moviecataloeguerepositoryarch.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.onoh.moviecataloeguerepositoryarch.R
import com.onoh.moviecataloeguerepositoryarch.vo.Status
import com.onoh.moviecataloeguerepositoryarch.vo.ViewModelFactory
import kotlinx.android.synthetic.main.layout_discovery_movie.*
import kotlinx.android.synthetic.main.layout_discovery_movie.dsv_progress_bar
import kotlinx.android.synthetic.main.layout_discovery_tv.*
import kotlinx.android.synthetic.main.layout_trending_movie.*

class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if(activity != null){
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this,factory)[HomeViewModel::class.java]

            val mvDsvAdapter = DiscoveryMvAdapter()
            val tvDsvAdapter = DiscoveryTvAdapter()
            val trendAdapter = TrendAdapter()

            viewModel.getDsvMovies().observe(viewLifecycleOwner, {
                if(it!=null){
                    when(it.status){
                        Status.LOADING ->dsv_progress_bar.visibility = View.VISIBLE
                        Status.SUCCESS-> {
                            dsv_progress_bar.visibility = View.GONE
                            mvDsvAdapter.submitList(it.data)
                            mvDsvAdapter.notifyDataSetChanged()
                        }
                        Status.ERROR -> {
                            dsv_progress_bar.visibility = View.GONE
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
            with(rv_dsv_movie){
                layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL,false)
                setHasFixedSize(true)
                adapter = mvDsvAdapter
            }

            viewModel.getDsvTv().observe(viewLifecycleOwner, {
                if(it!=null){
                    when(it.status){
                        Status.LOADING ->dsv_tv_progress_bar.visibility = View.VISIBLE
                        Status.SUCCESS-> {
                            dsv_tv_progress_bar.visibility = View.GONE
                            tvDsvAdapter.submitList(it.data)
                            tvDsvAdapter.notifyDataSetChanged()
                        }
                        Status.ERROR -> {
                            dsv_tv_progress_bar.visibility = View.GONE
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
            with(rv_dsv_tv){
                layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL,false)
                setHasFixedSize(true)
                adapter = tvDsvAdapter
            }

            viewModel.getTrending().observe(viewLifecycleOwner, {
                if(it!=null){
                    when(it.status){
                        Status.LOADING ->trend_progress_bar.visibility = View.VISIBLE
                        Status.SUCCESS-> {
                            trend_progress_bar.visibility = View.GONE
                            trendAdapter.submitList(it.data)
                            trendAdapter.notifyDataSetChanged()
                        }
                        Status.ERROR -> {
                            trend_progress_bar.visibility = View.GONE
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
            with(rv_trend){
                layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL,false)
                setHasFixedSize(true)
                adapter = trendAdapter
            }

        }
    }

}
