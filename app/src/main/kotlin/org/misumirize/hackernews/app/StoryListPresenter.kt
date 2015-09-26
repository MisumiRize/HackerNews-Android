package org.misumirize.hackernews.app

import android.os.Bundle
import rx.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class StoryListPresenter(val view: StoryView) {

    var hackerNewsService: HackerNewsService? = null
        @Inject set

    fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            hackerNewsService!!.getTopStories()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { stories -> view.onStories(stories) }
        } else {
            val stories = savedInstanceState.getSerializable("stories")
            view.onStories(stories as List<Story>)
        }
    }
}
