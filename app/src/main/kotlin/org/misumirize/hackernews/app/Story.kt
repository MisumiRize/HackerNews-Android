package org.misumirize.hackernews.app

import java.io.Serializable

class Story(val by: String,
            val descendants: String,
            val id: Int,
            val kids: List<Int>,
            val score: Int,
            // TODO: val time
            val text: String,
            val title: String,
            val type: String,
            val url: String) : Serializable {

    fun getDescription(): String {
        return "by ${this.by}"
    }
}
