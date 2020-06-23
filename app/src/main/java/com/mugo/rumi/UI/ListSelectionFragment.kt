package com.mugo.rumi.UI

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mugo.rumi.DataManager.ListDataManager

import com.mugo.rumi.R
import com.mugo.rumi.adapters.ListSelectionRecyclerViewAdapter
import com.mugo.rumi.models.TaskList
import kotlinx.android.synthetic.main.content_main.*
import java.lang.RuntimeException


class ListSelectionFragment : Fragment(), ListSelectionRecyclerViewAdapter.ListSelectionRecyclerViewClickListener {

    private var listener: OnListItemFragmentInteractionListener? = null
    lateinit var listDataManager: ListDataManager
    lateinit var listsRecyclerView: RecyclerView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListItemFragmentInteractionListener) {
            listener = context
            listDataManager = ListDataManager(context)
        } else {
            throw RuntimeException("$context must implement OnListItemFragmentInteractionListener")
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // Initialize recycler view
        val lists = listDataManager.readLists()
        listsRecyclerView = lists_recyclerview
        listsRecyclerView.layoutManager = LinearLayoutManager(this)
        listsRecyclerView.adapter = ListSelectionRecyclerViewAdapter(lists, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_selection, container, false)
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun listItemClicked(list: TaskList) {
        listener?.onListItemClicked(list)
    }

    fun addList(list: TaskList) {
        listDataManager.saveList(list)

        val recyclerAdapter = listRecyclerView.adapter as ListSelectionRecyclerViewAdapter
        recyclerAdapter.addList(list)
    }

    fun saveList(list: TaskList) {
        listDataManager.saveList(list)
        updateLists()
    }

    private fun updateLists() {
        val lists = listDataManager.readLists()
        listsRecyclerView.adapter = ListSelectionRecyclerViewAdapter(lists, this)
    }

    interface OnListItemFragmentInteractionListener {
        fun onListItemClicked(list: TaskList)
    }

    companion object {
        fun newInstance(): ListSelectionFragment {
            return ListSelectionFragment()
        }
    }


}
