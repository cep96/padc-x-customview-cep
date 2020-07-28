package com.cep.padc_x_customview_cep.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cep.padc_x_customview_cep.R
import com.cep.padc_x_customview_cep.adapters.SampleViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    private lateinit var mViewPagerAdapter : SampleViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        ivCancel.setOnClickListener { onBackPressed() }
        setUpViewPager()
    }

    private fun setUpViewPager() {
        mViewPagerAdapter = SampleViewPagerAdapter(this)
        viewpager2.isUserInputEnabled = false
        viewpager2.adapter = mViewPagerAdapter
        
        TabLayoutMediator(tabLayout, viewpager2){ tab, position ->
            when(position){
                0 -> {tab.text = "Project Tasks"}
                1 -> {tab.text = "Contacts"}
                2 -> {tab.text = "About you"}
            }
        }.attach()
    }

    companion object{
        fun newIntent(context: Context): Intent{
            return Intent(context, ProfileActivity::class.java)
        }
    }
}