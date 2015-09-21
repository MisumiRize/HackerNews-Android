package org.misumirize.hackernews.app

import android.os.Bundle
import android.support.v4.app.ListFragment
import java.util.*

class StoryListFragment : ListFragment(), StoryView {

    val presenter = StoryListPresenter(this)
    var adapter: StoryListAdapter? = null
    var stories: List<Story>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.getAppComponent().inject(presenter)
        adapter = StoryListAdapter(this.context)
        listAdapter = adapter
        presenter.onCreate(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putSerializable("stories", ArrayList<Story>(stories))
    }

    override fun onStories(stories: List<Story>) {
        this.stories = stories
        adapter!!.addAll(stories)
        adapter!!.notifyDataSetChanged()
    }
}
