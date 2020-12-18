package com.liao.loginunittesting.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridLayout
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.liao.loginunittesting.R
import com.liao.loginunittesting.adapter.AdapterRecyclerView
import com.liao.loginunittesting.model.DataClass
import com.liao.loginunittesting.view_model.HomeViewModel
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    lateinit var viewModel: HomeViewModel
    var list = listOf<DataClass>()
    lateinit var mAdapter: AdapterRecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        viewModel.getData()
        viewModel.myLiveData.observe(this) {
            if (it != null) {
                mAdapter.refreshList(it)
            }
        }
        //list = viewModel.getMockData()
        mAdapter = AdapterRecyclerView(this, list)

        recycler_view.apply {
            layoutManager = GridLayoutManager(this@HomeActivity,2)
            adapter = mAdapter
            addItemDecoration(DividerItemDecoration(this@HomeActivity, DividerItemDecoration.VERTICAL))
            addItemDecoration(DividerItemDecoration(this@HomeActivity, DividerItemDecoration.HORIZONTAL))
        }
    }
}