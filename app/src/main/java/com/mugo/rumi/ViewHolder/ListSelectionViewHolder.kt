package com.mugo.rumi.ViewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_selection_viewholder.view.*

class ListSelectionViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    val listPosition = itemView.itemNumber
    val listTitle = itemView.itemString

}