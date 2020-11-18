package com.onoh.moviecataloeguerepositoryarch.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import com.onoh.moviecataloeguerepositoryarch.R
import com.onoh.moviecataloeguerepositoryarch.utils.DataDummy
import com.onoh.moviecataloeguerepositoryarch.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest{

    private val dummyDsvMv = DataDummy.generateDummyDsvMovieResponse()
    private val dummyDsvTv = DataDummy.generateDummyDsvTvResponse()
    private val dummyTrend = DataDummy.generateDummyTrendResponse()
    private val dummyMovie = DataDummy.generateDummyMoviesResponse()
    private val dummyTv = DataDummy.generateDummyTvShowsResponse()

    @get:Rule
    var mActivityRule = ActivityTestRule(MainActivity::class.java)


    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @Test
    fun loadTrendMovie(){
        Espresso.onView(withId(R.id.rv_trend)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rv_trend)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyTrend.size
            )
        )
    }

    @Test
    fun loadDsvMovie(){
        Espresso.onView(withId(R.id.rv_dsv_movie)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rv_dsv_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyDsvMv.size
            )
        )
    }

    @Test
    fun loadDsvTv(){
        Espresso.onView(withId(R.id.nested_scrollview_home)).perform(ViewActions.swipeUp())
        Espresso.onView(withId(R.id.rv_dsv_tv)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rv_dsv_tv)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyDsvTv.size
            )
        )
    }

    @Test
    fun loadMovie(){
        Espresso.onView(withId(R.id.navigation_dashboard)).perform(click())
        Espresso.onView(withId(R.id.rv_movies)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
    }

    @Test
    fun loadTvshow(){
        Espresso.onView(withId(R.id.navigation_dashboard)).perform(click())
        Espresso.onView(withText("Tv Shows")).perform(click())
        Espresso.onView(withId(R.id.rv_tv_show))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTv.size))
    }

    @Test
    fun loadDetailMovie(){
        Espresso.onView(withId(R.id.navigation_dashboard)).perform(click())
        Espresso.onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Espresso.onView(withId(R.id.nested_scrollview_detail_mv)).perform(ViewActions.swipeUp())
        Espresso.onView(withId(R.id.tv_detail_title_mv)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_detail_title_mv)).check(ViewAssertions.matches(withText(dummyDsvMv[8].title)))
        Espresso.onView(withId(R.id.tv_detail_overview_mv)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_detail_overview_mv)).check(ViewAssertions.matches(withText(dummyDsvMv[8].overview)))
    }

    @Test
    fun loadDetailTv(){
        Espresso.onView(withId(R.id.navigation_dashboard)).perform(click())
        Espresso.onView(withText("Tv Shows")).perform(click())
        Espresso.onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Espresso.onView(withId(R.id.nested_scrollview_detail_tv)).perform(ViewActions.swipeUp())
        Espresso.onView(withId(R.id.tv_detail_title_tv)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_detail_title_tv)).check(ViewAssertions.matches(withText(
            dummyTv[12].originalName
        )))
        Espresso.onView(withId(R.id.tv_detail_overview_tv)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_detail_overview_tv)).check(ViewAssertions.matches(withText(dummyTv[12].overview)))
    }
}