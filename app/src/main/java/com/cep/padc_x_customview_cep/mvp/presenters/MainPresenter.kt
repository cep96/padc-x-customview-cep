package com.cep.padc_x_customview_cep.mvp.presenters

import com.cep.padc_x_customview_cep.mvp.views.MainView

interface MainPresenter: BasePresenter<MainView> {

    fun onProfileUIReady()
    fun onTaskUIReady()
}