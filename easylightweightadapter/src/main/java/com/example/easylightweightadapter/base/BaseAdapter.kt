package com.example.easylightweightadapter.base

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 *
 * Easy adapter that represent any type of data with DiffUtil implementation.
 *
 * Whenever you create an adapter just inherit from BaseAdapter
 * and provide 3 types: List type, ViewHolder class and DiffUtil class
 * This infrastructure will do the rest
 *
 * Use it like.
 * <pre>
 *      mAdapter = new SomeAdapter();
 *      mAdapter.setDataSource(List<Any>);
 * </pre>
 * @author Yarik S.
 *
 */
abstract class BaseAdapter<T, VH : BaseAdapter.BaseViewHolder<T>, DU : BaseDiffUtil<T>> : RecyclerView.Adapter<VH>() {

    var mItems: MutableList<T> = arrayListOf()

    lateinit var mDiffUtil: DU

    private fun getItem(position: Int) = mItems[position]

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(getItemViewId(), parent, false)
        return instantiateViewHolder(view)
    }
    abstract fun getItemViewId() : Int

    abstract fun getDiffUtilCallback() : DU

    abstract fun instantiateViewHolder(view: View): VH

    override fun getItemCount(): Int = mItems.size

    abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView){
        abstract fun bind(data: T)
    }

    fun setItems(data: List<T>) : Disposable {
        mDiffUtil = getDiffUtilCallback()
        mDiffUtil.oldList = mItems
        mDiffUtil.newList = data

        return Flowable.fromArray(data)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .map { DiffUtil.calculateDiff(mDiffUtil) }
                .doOnNext { mItems = data.toMutableList() }
                .subscribe { it.dispatchUpdatesTo(this) }
    }

    fun addItem(data: T) : Disposable{
        val newList = mItems
        newList.add(data)

        mDiffUtil = getDiffUtilCallback()
        mDiffUtil.oldList = mItems
        mDiffUtil.newList = newList

        return Flowable.fromArray(data)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .map { DiffUtil.calculateDiff(mDiffUtil) }
                .doOnNext { mItems.add(data)}
                .subscribe { it.dispatchUpdatesTo(this) }
    }

}