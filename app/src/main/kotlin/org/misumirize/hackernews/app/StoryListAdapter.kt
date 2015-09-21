package org.misumirize.hackernews.app

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class StoryListAdapter(context: Context) : ArrayAdapter<Story>(context, 0) {

    val layoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val view: View
        if (convertView == null) {
            view = layoutInflater.inflate(R.layout.list_story_item, parent, false)
        } else {
            view = convertView
        }

        val story = getItem(position)
        (view.findViewById(R.id.text_title) as TextView).text = story.title
        (view.findViewById(R.id.text_description) as TextView).text = story.getDescription()

        return view
    }
}
