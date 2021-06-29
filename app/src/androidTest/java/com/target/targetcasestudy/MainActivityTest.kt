package com.target.targetcasestudy

import android.content.Intent
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import junit.framework.TestCase
import org.hamcrest.Matchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(androidx.test.ext.junit.runners.AndroidJUnit4::class)
class MainActivityTest : TestCase() {

    @Rule
    lateinit var activityTest: ActivityTestRule<MainActivity>

    @Before
    public override fun setUp() {
        activityTest = ActivityTestRule<MainActivity>(MainActivity::class.java)
        super.setUp()
    }

    @Test
    fun openHomePage(){
        activityTest.launchActivity(Intent())
        Thread.sleep(2000)
    }

    @Test
    fun openHomeAndCreditMenu(){
        activityTest.launchActivity(Intent())
        Thread.sleep(2000)
        var view = ViewMatchers.withId(R.id.credit_card)
        assertNotNull(view)
        Espresso.onView(allOf(ViewMatchers.withId(R.id.credit_card), ViewMatchers.isDisplayed()))
            .perform(ViewActions.click())
        Thread.sleep(5000)
    }


    @Test
    fun homePageRecyclerView(){
        activityTest.launchActivity(Intent())
        Thread.sleep(3000)
        var view = ViewMatchers.withId(R.id.recycler_view)
        assertNotNull(view)
        Espresso.onView(allOf(view, ViewMatchers.isDisplayed()))
            .perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5),
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(8),
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    5,
                    ViewActions.click()
                )
            )
        Thread.sleep(3000)
    }


    @Test
    fun detailsPageScroll(){
        activityTest.launchActivity(Intent())
        Thread.sleep(3000)
        Espresso.onView(allOf(ViewMatchers.withId(R.id.recycler_view), ViewMatchers.isDisplayed()))
            .perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5),
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(8),
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    5,
                    ViewActions.click()
                )
            )
        Thread.sleep(2000)
        Espresso.onView(allOf(ViewMatchers.withId(R.id.deals_description)))
            .perform(ViewActions.scrollTo())
        Thread.sleep(2000)
    }


    @After
    public override fun tearDown() {}
}