package com.cep.padc_x_customview_cep.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.cep.padc_x_customview_cep.R
import com.cep.padc_x_customview_cep.adapters.ProfileRecyclerAdapter
import com.cep.padc_x_customview_cep.delegates.ProfileDelegate
import com.cep.padc_x_customview_cep.utils.OverlapRecycler
import kotlinx.android.synthetic.main.activity_create_new_task.*

class CreateNewTaskActivity : AppCompatActivity(), ProfileDelegate {

    private lateinit var mProfileAdapter: ProfileRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_task)

        setUpRecycler()
    }

    private fun setUpRecycler() {
        val strArray = arrayListOf<String>("1", "2", "3", "4")
        mProfileAdapter = ProfileRecyclerAdapter(this, strArray.size)
        mProfileAdapter.setNewData(strArray)

        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvAssignee.layoutManager = linearLayoutManager
        rvAssignee.addItemDecoration(OverlapRecycler(-100))
        rvAssignee.adapter = mProfileAdapter
    }

    companion object{
        fun newIntent(context: Context): Intent{
            return Intent(context, CreateNewTaskActivity::class.java)
        }
    }

    override fun onTapItem(status: Int) {
        if (status == 0){
            startActivity(ProfileActivity.newIntent(this))
        }else{
            startActivity(CreateNewTaskActivity.newIntent(this))
        }
    }

}