package com.habib.recyclerviewpoc.adapter

import android.view.View
import android.widget.TextView
import com.habib.recyclerviewpoc.R
import com.example.easylightweightadapter.base.BaseAdapter
import com.example.easylightweightadapter.base.BaseDiffUtil
import com.habib.recyclerviewpoc.models.ThisIsPojo
import kotlinx.android.synthetic.main.item_test.view.*

/**
 * Created by Yarik on 27/08/2018.:)
 */
class ExampleAdapter(var listener: RecyclerViewItemClick<ThisIsPojo>) : BaseAdapter<ThisIsPojo, ExampleAdapter.ViewHolder, ExampleAdapter.TestDiffCallback>() {

    override fun getDiffUtilCallback(): TestDiffCallback = TestDiffCallback(arrayListOf(), arrayListOf())

    override fun instantiateViewHolder(view: View): ViewHolder = ViewHolder(view)

    override fun getItemViewId() = R.layout.item_test

    inner class ViewHolder(itemView: View) : BaseViewHolder<ThisIsPojo>(itemView) {

        private val textView: TextView = itemView.text_view

        override fun bind(data: ThisIsPojo) {
            textView.text = data.str
            textView.setOnClickListener { listener.onClick(itemView, data) }
            textView.setOnLongClickListener { listener.onLongClick(itemView, data) }

        }
    }
    inner class TestDiffCallback(oldList: List<ThisIsPojo>, newList: List<ThisIsPojo>) : BaseDiffUtil<ThisIsPojo>(oldList, newList) {

        override fun itemsSame(oldItem: ThisIsPojo, newItem: ThisIsPojo): Boolean {
            return oldItem.str == newItem.str
        }
    }
}