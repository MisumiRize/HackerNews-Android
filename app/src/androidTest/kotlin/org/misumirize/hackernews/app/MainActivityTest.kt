package org.misumirize.hackernews.app

import android.content.Intent
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v4.app.ListFragment
import android.test.suitebuilder.annotation.LargeTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertEquals

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    private val activityRule = ActivityTestRule(MainActivity::class.java)
    var activity: MainActivity? = null

    @Rule
    public fun getActivityRule(): ActivityTestRule<MainActivity> = activityRule

    @Before
    fun setUp() {
        activityRule.launchActivity(Intent())
        activity = activityRule.activity
    }

    @Test
    fun storyListFragmentWillDisplayStories() {
        val fragment = activity!!.supportFragmentManager.findFragmentById(R.id.container) as ListFragment
        if (BuildConfig.NETWORK_MOCKED) {
            assertEquals(fragment.listView.count, 30)
        } else {
            assertEquals(fragment.listView.count, 0)
        }
    }
}
