package com.android.aop.part2.toycoininformation.presentation.list

import com.android.aop.part2.toycoininformation.domain.model.Coin
import com.android.aop.part2.toycoininformation.presentation.base.ViewState

sealed class CoinListViewState : ViewState {

    data class GetCoinList(val list: List<Coin>) : CoinListViewState()
    data class Error(val errorMessage: String) : CoinListViewState()
    data class Loading(val isLoading: Boolean) : CoinListViewState()
}
