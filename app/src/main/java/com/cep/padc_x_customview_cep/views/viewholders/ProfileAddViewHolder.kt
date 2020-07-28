package com.cep.padc_x_customview_cep.views.viewholders

import android.view.View
import com.cep.padc_x_customview_cep.delegates.ProfileDelegate

class ProfileAddViewHolder(
    itemView: View,
private val delegate: ProfileDelegate) : ProfileBaseViewHolder(itemView) {

    init {
        itemView.setOnClickListener {
            delegate.onTapItem(1)
        }
    }

    override fun bindData(data: String) {

    }
}