package com.android.aop.part2.toycoininformation.presentation.detail

import com.android.aop.part2.toycoininformation.domain.model.CoinDetail
import com.android.aop.part2.toycoininformation.presentation.base.ViewState

sealed class CoinDetailViewState : ViewState {

    object RouteCoinList : CoinDetailViewState()
    data class GetCoinDetailList(val list: List<CoinDetail>) : CoinDetailViewState()
    data class Error(val errorMessage: String) : CoinDetailViewState()
    data class Loading(val isLoading: Boolean) : CoinDetailViewState()

}

