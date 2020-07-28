package com.cep.padc_x_customview_cep.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.cep.padc_x_customview_cep.R
import com.cep.padc_x_customview_cep.views.viewholders.TaskViewHolder

class TaskRecyclerAdapter: BaseRecyclerAdapter<TaskViewHolder, Int>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_task, parent, false)
        )
    }
}