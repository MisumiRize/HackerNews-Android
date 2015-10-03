package org.misumirize.hackernews.app

import android.content.Intent
import android.support.test.espresso.Espresso
import android.support.test.espresso.IdlingResource
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.test.suitebuilder.annotation.LargeTest
import android.widget.ListView
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*
import kotlin.test.assertEquals

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    class StoriesIdlingResource(val stories: ArrayList<Story>) : IdlingResource {

        var resourceCallback: IdlingResource.ResourceCallback? = null

        override fun getName(): String? {
            return this.javaClass.name
        }

        override fun isIdleNow(): Boolean {
            if (stories.size() != 0) {
                resourceCallback?.onTransitionToIdle()
                return true
            }

            return false
        }

        override fun registerIdleTransitionCallback(resourceCallback: IdlingResource.ResourceCallback?) {
            this.resourceCallback = resourceCallback
        }

    }

    private val activityRule = ActivityTestRule(MainActivity::class.java)
    var idlingResource: StoriesIdlingResource? = null

    @Rule
    public fun getActivityRule(): ActivityTestRule<MainActivity> = activityRule

    @Before
    fun setUp() {
        activityRule.launchActivity(Intent())
        val activity = activityRule.activity
        val fragment = activity.supportFragmentManager.findFragmentById(R.id.container) as StoryListFragment
        idlingResource = StoriesIdlingResource(fragment.stories)
        Espresso.registerIdlingResources(idlingResource)
    }

    @Test
    fun testStoryListFragmentWillDisplayStories() {
        Espresso.onView(ViewMatchers.withClassName(Matchers.endsWith("ListView")))
                .check { view, noMatchingViewException ->
                    assertEquals((view!! as ListView).count, 30)
                }
    }

    @After
    fun tearDown() {
        Espresso.unregisterIdlingResources(idlingResource)
    }
}
