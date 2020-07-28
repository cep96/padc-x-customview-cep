package com.cep.padc_x_customview_cep.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.cep.padc_x_customview_cep.fragments.SampleViewPagerFragment

class SampleViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        when(position){
            0 -> {
                return SampleViewPagerFragment()
            }
            1 -> {
                return SampleViewPagerFragment()
            }
            2 -> {
                return SampleViewPagerFragment()
            }
            else -> return SampleViewPagerFragment()
        }
    }
}