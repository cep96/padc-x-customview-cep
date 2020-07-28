package com.cep.padc_x_customview_cep.mvp.presenters

import com.cep.padc_x_customview_cep.mvp.views.BaseView

interface BasePresenter<T: BaseView> {

    fun initPresenter(view: T)
}