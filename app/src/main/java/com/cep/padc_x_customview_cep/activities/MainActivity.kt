package com.cep.padc_x_customview_cep.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.cep.padc_x_customview_cep.R
import com.cep.padc_x_customview_cep.adapters.ProfileRecyclerAdapter
import com.cep.padc_x_customview_cep.adapters.TaskRecyclerAdapter
import com.cep.padc_x_customview_cep.delegates.ProfileDelegate
import com.cep.padc_x_customview_cep.mvp.presenters.MainPresenter
import com.cep.padc_x_customview_cep.mvp.presenters.MainPresenterImpl
import com.cep.padc_x_customview_cep.mvp.views.MainView
import com.cep.padc_x_customview_cep.utils.OverlapRecycler
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.layout_profile.*

class MainActivity : AppCompatActivity(), MainView , ProfileDelegate{

    private lateinit var mProfileAdapter: ProfileRecyclerAdapter
    private lateinit var mTaskAdapter: TaskRecyclerAdapter

    private lateinit var mPresenter: MainPresenter

    private var strArray = arrayListOf<String>("1", "2", "3", "4")
    private var intArray = arrayListOf<Int>(1,2,3,4)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpPresenter()
        setUpProfileRecyclerView()
        setUpTaskRecyclerView()

    }

    private fun setUpTaskRecyclerView() {
        mTaskAdapter = TaskRecyclerAdapter()
        mTaskAdapter.setNewData(intArray)

        val linearLayoutManager = LinearLayoutManager(this)
        rvTasks.layoutManager = linearLayoutManager
        rvTasks.adapter = mTaskAdapter
    }

    private fun setUpProfileRecyclerView() {
        mProfileAdapter = ProfileRecyclerAdapter(this, strArray.size)
        mProfileAdapter.setNewData(strArray)

        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvProfile.layoutManager = linearLayoutManager
        rvProfile.addItemDecoration(OverlapRecycler(-100))
        rvProfile.adapter = mProfileAdapter
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProviders.of(this).get(MainPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    override fun displayProfileList(str: List<String>) {
        mProfileAdapter.setNewData(str.toMutableList())
    }

    override fun displayTaskList(tasks: List<String>) {
        TODO("Not yet implemented")
    }

    override fun navigateToProfileDetails(str: String) {
        TODO("Not yet implemented")
    }

    override fun navigateToCreateNewTask() {
        TODO("Not yet implemented")
    }

    override fun onTapItem(status: Int) {
        if (status == 0){
            startActivity(ProfileActivity.newIntent(this))
        }else{
            startActivity(CreateNewTaskActivity.newIntent(this))
        }
    }


}