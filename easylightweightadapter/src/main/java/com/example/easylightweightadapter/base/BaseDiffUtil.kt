package com.example.easylightweightadapter.base

import android.support.v7.util.DiffUtil

abstract class BaseDiffUtil<T>(var oldList: List<T>, var newList: List<T>) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    private fun getOldItem(pos: Int): T {
        return oldList[pos]
    }

    private fun getNewItem(pos: Int): T {
        return newList[pos]
    }


    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return contentSame(oldItemPosition, newItemPosition)
    }

    protected open fun contentSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return itemsSame(getOldItem(oldItemPosition), getNewItem(newItemPosition))
    }

    abstract fun itemsSame(oldItem: T, newItem: T): Boolean

}