package com.cep.padc_x_customview_cep.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.cep.padc_x_customview_cep.R
import com.cep.padc_x_customview_cep.delegates.ProfileDelegate
import com.cep.padc_x_customview_cep.views.viewholders.ProfileAddViewHolder
import com.cep.padc_x_customview_cep.views.viewholders.ProfileBaseViewHolder
import com.cep.padc_x_customview_cep.views.viewholders.ProfileViewHolder

class ProfileRecyclerAdapter(
    private val delegate: ProfileDelegate,
    private val size: Int
): BaseRecyclerAdapter<ProfileBaseViewHolder, String>() {

    companion object{
        const val VIEW_TYPE_ADD = 111
        const val VIEW_TYPE_PROFILE = 222
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileBaseViewHolder {

        when(viewType){
            VIEW_TYPE_ADD ->{
                return ProfileAddViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_create, parent, false),
                    delegate
                )
            }
            VIEW_TYPE_PROFILE -> {
                return ProfileViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_profile, parent, false),
                    delegate
                )
            }
        }
        return ProfileViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_profile, parent, false),
            delegate
        )
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == size-1){
            VIEW_TYPE_ADD
        }else{
            VIEW_TYPE_PROFILE
        }
    }
}