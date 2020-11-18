package com.onoh.moviecataloeguerepositoryarch.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.onoh.moviecataloeguerepositoryarch.R
import com.onoh.moviecataloeguerepositoryarch.data.local.entity.TvShowEntity
import com.onoh.moviecataloeguerepositoryarch.vo.Resource
import com.onoh.moviecataloeguerepositoryarch.vo.Status
import com.onoh.moviecataloeguerepositoryarch.vo.ViewModelFactory
import kotlinx.android.synthetic.main.activity_detail_mv.btn_close_detail
import kotlinx.android.synthetic.main.activity_detail_mv.progressBar
import kotlinx.android.synthetic.main.activity_detail_tv.*
import kotlinx.android.synthetic.main.layout_cast.*

class DetailTvActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TV = "extra_tv"
        const val IMAGE_URL = "https://image.tmdb.org/t/p/w500/"
    }

    private lateinit var viewModel: DetailViewModel
    private var menuDetail: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tv)
        setSupportActionBar(toolbar_detail_tv)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        btn_close_detail.setOnClickListener{finish()}

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this,factory)[DetailViewModel::class.java]
        val extras = intent.extras
        if(extras != null){
            val tvId = extras.getInt(EXTRA_TV)
            if(tvId!= 0){
                progressBar.visibility = View.VISIBLE
                viewModel.setSelectedtvShow(tvId)
                setupDetail(viewModel.detailTvShow)
            }
        }
    }

    private fun setupDetail(detailTv: LiveData<Resource<TvShowEntity>>) {
        detailTv.observe(this, {
            if(it!=null){
                when(it.status){
                    Status.LOADING -> progressBar.visibility = View.VISIBLE
                    Status.SUCCESS -> if (it.data != null) {
                        progressBar.visibility = View.GONE
                        Glide.with(this)
                            .load(IMAGE_URL +it.data.posterImage)
                            .apply(
                                RequestOptions.placeholderOf(R.drawable.ic_loading)
                                    .error(R.drawable.ic_error))
                            .into(img_poster_detail_tv)

                        Glide.with(this)
                            .load(IMAGE_URL +it.data.backdropImage)
                            .apply(
                                RequestOptions.placeholderOf(R.drawable.ic_loading)
                                    .error(R.drawable.ic_error))
                            .into(img_backdrop_detail_tv)

                        tv_detail_title_tv.text = it.data.title
                        tv_detail_rating_tv.text = it.data.rating.toString()
                        tv_detail_overview_tv.text = it.data.overview
                    }
                    Status.ERROR -> {
                        progressBar.visibility = View.GONE
                        Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        })
        cast_progress_bar.visibility = View.VISIBLE
        val castTvAdapter = CastAdapter("tvShow")
        viewModel.getCastTv().observe(this, {
            if(it!=null){
                cast_progress_bar.visibility = View.GONE
                castTvAdapter.setTvCast(it)
                castTvAdapter.notifyDataSetChanged()
            }
        })
        with(rv_cast){
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL,false)
            setHasFixedSize(true)
            adapter = castTvAdapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menuDetail = menu
        viewModel.detailTvShow.observe(this,{
            if (it != null) {
                when (it.status) {
                    Status.LOADING -> progressBar.visibility = View.VISIBLE
                    Status.SUCCESS -> if (it.data != null) {
                        progressBar.visibility = View.GONE
                        val state = it.data.favorite
                        setFavoriteState(state)
                    }
                    Status.ERROR -> {
                        progressBar.visibility = View.GONE
                        Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_favorite) {
            viewModel.setTvFavorite()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setFavoriteState(state: Boolean) {
        val menuItem = menuDetail?.findItem(R.id.action_favorite)
        if (state) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite)
        } else {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_border)
        }
    }
}
