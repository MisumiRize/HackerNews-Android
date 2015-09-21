package org.misumirize.hackernews.app

import retrofit.RestAdapter
import retrofit.client.Client
import retrofit.http.GET
import retrofit.http.Path
import rx.Observable
import rx.schedulers.Schedulers

class HackerNewsService(client: Client) {

    val api = RestAdapter.Builder()
            .setEndpoint("https://hacker-news.firebaseio.com/v0")
            .setClient(client)
            .build()
            .create(HackerNewsAPI::class.java)

    fun getTopStories(size: Int = 30): Observable<List<Story>> {
        return api.getTopStories().flatMap { ids ->
            Observable.merge(ids.take(size).map { id -> api.getStory(id) }, 5)
                    .toSortedList { s1, s2 -> ids.indexOf(s1.id).compareTo(ids.indexOf(s2.id)) }
        }.subscribeOn(Schedulers.newThread())
    }

    fun getComments(story: Story): Observable<List<Comment>> {
        val kids = story.kids
        return Observable.merge(kids.map { id -> api.getComment(id) }, 5)
                .toSortedList { m1, m2 -> kids.indexOf(m1.id).compareTo(kids.indexOf(m2.id)) }
                .subscribeOn(Schedulers.newThread())
    }

    interface HackerNewsAPI {

        @GET("/topstories.json")
        fun getTopStories(): Observable<List<Int>>

        @GET("/item/{id}.json")
        fun getStory(@Path("id") id: Int): Observable<Story>

        @GET("/item/{story}.json")
        fun getComment(@Path("id") id: Int): Observable<Comment>
    }
}
