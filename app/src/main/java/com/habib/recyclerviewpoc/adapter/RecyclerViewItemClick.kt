package com.habib.recyclerviewpoc.adapter

import android.view.View

/**
 * Created by Yarik on 27/08/2018.:)
 */

interface RecyclerViewItemClick<T> {
    fun onClick(view: View, item: T)
    fun onLongClick(view: View, item: T): Boolean
}