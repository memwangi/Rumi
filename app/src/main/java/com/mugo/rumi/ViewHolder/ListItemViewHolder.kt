package com.mugo.rumi.ViewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.task_view_holder.view.*

class ListItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val taskTextView = itemView.textview_task
}