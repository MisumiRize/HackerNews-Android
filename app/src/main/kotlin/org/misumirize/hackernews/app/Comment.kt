package org.misumirize.hackernews.app

import java.io.Serializable

class Comment(val by: String,
              val id: Int,
              val parent: Int,
              val text: String,
              // TODO: val time
              val type: String) : Serializable
