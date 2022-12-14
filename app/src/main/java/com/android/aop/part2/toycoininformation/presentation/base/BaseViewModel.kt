package com.android.aop.part2.toycoininformation.presentation.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.aop.part2.toycoininformation.ext.uiScope

abstract class BaseViewModel(app: Application): AndroidViewModel(app){

    private val _viewStateLiveData = MutableLiveData<ViewState>()
    val viewStateLiveData: LiveData<ViewState> = _viewStateLiveData

    protected fun viewStateChanged(viewState: ViewState) {
        uiScope {
            _viewStateLiveData.value = viewState
            _viewStateLiveData.value = null
        }
    }
}

interface ViewState