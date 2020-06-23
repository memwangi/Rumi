package com.mugo.rumi.DataManager

import android.content.Context
import android.preference.PreferenceManager
import com.mugo.rumi.models.TaskList
import java.util.HashSet

class ListDataManager(private val context: Context) {
    fun saveList(list: TaskList) {
        val sharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(context).edit()
        sharedPreferences.putStringSet(list.name, list.tasks.toHashSet())
        sharedPreferences.apply()

    }

    fun readLists(): ArrayList<TaskList> {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val sharedPreferenceContents = sharedPreferences.all
        val taskLists = ArrayList<TaskList>()

        for (taskList in sharedPreferenceContents) {
            val itemsHashSet = ArrayList(taskList.value as HashSet<String>)
            val list = TaskList(taskList.key, itemsHashSet)
            taskLists.add(list)
        }

        return taskLists
    }
}