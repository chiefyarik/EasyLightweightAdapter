package com.habib.recyclerviewpoc

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

import com.habib.recyclerviewpoc.adapter.ExampleAdapter
import com.habib.recyclerviewpoc.models.ThisIsPojo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var mRecyclerView: RecyclerView
    lateinit var mFab: FloatingActionButton
    lateinit var mAdapter: ExampleAdapter

    private val strings: MutableList<ThisIsPojo> = arrayListOf(ThisIsPojo("Hello"), ThisIsPojo("This"), ThisIsPojo("is"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //init
        mFab = main_fab
        mRecyclerView = recycler_view
        mRecyclerView.layoutManager = LinearLayoutManager(this)

        mAdapter = ExampleAdapter()
        mAdapter.setItems(strings)

        mRecyclerView.adapter = mAdapter

        mFab.setOnClickListener{ additem() }
    }

    private fun additem() {
        if (strings.size > 3){
            mAdapter.addItem(ThisIsPojo("POJO"))
        } else {
            mAdapter.addItem(ThisIsPojo("TEST"))
        }

    }
}
