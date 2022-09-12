package com.android.aop.part2.toycoininformation.presentation.list

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.android.aop.part2.toycoininformation.domain.use_case.get_coin.GetCoinUseCase
import com.android.aop.part2.toycoininformation.ext.Resource
import com.android.aop.part2.toycoininformation.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    app: Application,
    private val getCoinUseCase: GetCoinUseCase
) : BaseViewModel(app) {

    val coinSearchLiveData = MutableLiveData<String>()

    val actionDoneListener: Function1<Boolean, Unit> = this::onActionDone

    init {
        getList()
    }

    private fun getList() {
        getCoinUseCase().onEach { resource ->
            when (resource) {
                is Resource.Success -> {
                    viewStateChanged(CoinListViewState.GetCoinList(resource.data.orEmpty()))
                    viewStateChanged(CoinListViewState.Loading(false))
                }

                is Resource.Error -> {
                    viewStateChanged(CoinListViewState.Error(resource.message.orEmpty()))
                    viewStateChanged(CoinListViewState.Loading(false))
                }

                is Resource.Loading -> {
                    viewStateChanged(CoinListViewState.Loading(true))
                }
            }
        }.launchIn(CoroutineScope(Dispatchers.IO))
    }

    private fun getSearchItem(searchItem: String) {
        getCoinUseCase().onEach { resource ->
            when (resource) {
                is Resource.Success -> {

                    val toFilterList = resource.data?.filter { it.name.lowercase() == searchItem }

                    if (!toFilterList.isNullOrEmpty()) {
                        viewStateChanged(CoinListViewState.GetCoinList(toFilterList))
                        viewStateChanged(CoinListViewState.Loading(false))
                    } else {
                        getList()
//                        viewStateChanged(CoinListViewState.GetCoinList(resource.data.orEmpty()))
//                        viewStateChanged(CoinListViewState.Loading(false))
                    }
                }

                is Resource.Error -> {
                    viewStateChanged(CoinListViewState.Error(resource.message.orEmpty()))
                    viewStateChanged(CoinListViewState.Loading(false))
                }

                is Resource.Loading -> {
                    viewStateChanged(CoinListViewState.Loading(true))
                }
            }
        }.launchIn(CoroutineScope(Dispatchers.IO))
    }

    private fun onActionDone(isActionDone: Boolean) {
        if (isActionDone) {
            coinSearchLiveData.value?.let {
                if (it.isEmpty()) {
                    getList()
                } else {
                    getSearchItem(it)
                }
            }
        }
    }
}