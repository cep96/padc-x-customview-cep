package com.cep.padc_x_customview_cep.mvp.views

interface MainView: BaseView {

    fun displayProfileList(str: List<String>)
    fun displayTaskList(tasks: List<String>)
    fun navigateToProfileDetails(str: String)
    fun navigateToCreateNewTask()
}