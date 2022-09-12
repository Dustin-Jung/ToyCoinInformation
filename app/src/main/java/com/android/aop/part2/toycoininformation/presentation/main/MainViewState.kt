package com.android.aop.part2.toycoininformation.presentation.main

import com.android.aop.part2.toycoininformation.domain.model.Coin
import com.android.aop.part2.toycoininformation.presentation.base.ViewState

sealed class MainViewState : ViewState {

    data class  RouteDetail(val item: Coin) : MainViewState()
    data class Error(val message: String) : MainViewState()
}
