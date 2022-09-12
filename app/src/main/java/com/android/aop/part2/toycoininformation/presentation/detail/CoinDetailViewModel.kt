package com.android.aop.part2.toycoininformation.presentation.detail

import android.app.Application
import androidx.databinding.ObservableField
import com.android.aop.part2.toycoininformation.domain.use_case.get_coins.GetCoinsUseCase
import com.android.aop.part2.toycoininformation.ext.Resource
import com.android.aop.part2.toycoininformation.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    app: Application,
    private val getCoinsUseCase: GetCoinsUseCase
) : BaseViewModel(app) {

    val rankAndNameObservableField = ObservableField<String>()
    val symbolObservableField = ObservableField<String>()
    val descriptionObservableField = ObservableField<String>()
    val tagsObservableField = ObservableField<String>()
    val teamObservableField = ObservableField<String>()

    fun getDetailList(coinId: String?) {
        coinId?.let {
            getCoinsUseCase(coinId).onEach { resource ->
                when (resource) {
                    is Resource.Success -> {
                        rankAndNameObservableField.set("${resource.data?.rank}. ${resource.data?.name}")
                        symbolObservableField.set("${resource.data?.symbol}")
                        descriptionObservableField.set("${resource.data?.description}")


                        val tagResult = """
                            name : ${resource.data?.tags?.joinToString(separator = ", ") { it.name }}
                        """.trimIndent()

                        tagsObservableField.set(tagResult)

                        val teamResult = """
                            name : ${resource.data?.team?.joinToString(separator = ", ") { it.name }}
                        """.trimIndent()

                        teamObservableField.set(teamResult)

                    }

                    is Resource.Error -> {
                        viewStateChanged(CoinDetailViewState.Error(resource.message.orEmpty()))
                        viewStateChanged(CoinDetailViewState.Loading(false))
                    }
                }
            }.launchIn(CoroutineScope(Dispatchers.IO))
        }
    }

    fun routeCoinList() {
        viewStateChanged(CoinDetailViewState.RouteCoinList)
    }
}