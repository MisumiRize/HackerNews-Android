package org.misumirize.hackernews.app

import android.os.Bundle

class StoryListPresenter(val view: StoryView) {

    fun onCreate(savedInstanceState: Bundle?) {
        view.onStories(0.until(30).map { i ->
            Story(
                    by = "user",
                    descendants = "foobar",
                    id = 1,
                    kids = listOf(10, 11),
                    score = 1,
                    text = "this is text",
                    title = "title",
                    url = "http://example.com"
            )
        })
    }
}
