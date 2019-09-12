package com.douyao.demokotlin.ui.home

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val mText: MutableLiveData<String>

    val text: LiveData<String>
        get() = mText

    init {
        mText = MutableLiveData()
        mText.value = "This is home fragment"
    }
}