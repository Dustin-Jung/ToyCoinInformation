package com.android.aop.part2.toycoininformation.presentation.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.aop.part2.toycoininformation.domain.model.Coin
import com.android.aop.part2.toycoininformation.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(app: Application): BaseViewModel(app) {

    private val _mainViewStateLiveData = MutableLiveData<MainViewState>()
    val mainViewStateLiveData: LiveData<MainViewState> = _mainViewStateLiveData

    fun routeDetail(item: Coin){
        _mainViewStateLiveData.value = MainViewState.RouteDetail(item)
    }
}