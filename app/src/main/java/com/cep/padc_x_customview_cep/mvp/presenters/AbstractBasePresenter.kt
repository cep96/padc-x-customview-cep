package com.cep.padc_x_customview_cep.mvp.presenters

import androidx.lifecycle.ViewModel
import com.cep.padc_x_customview_cep.mvp.views.BaseView

abstract class AbstractBasePresenter<T: BaseView>: BasePresenter<T>, ViewModel(){

    var mView: T ?= null

    override fun initPresenter(view: T) {
        mView = view
    }
}