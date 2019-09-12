package com.douyao.demokotlin.ui.dashboard

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {

    private val mText: MutableLiveData<String>

    val text: LiveData<String>
        get() = mText

    init {
        mText = MutableLiveData()
        mText.value = "This is dashboard fragment"
    }
}