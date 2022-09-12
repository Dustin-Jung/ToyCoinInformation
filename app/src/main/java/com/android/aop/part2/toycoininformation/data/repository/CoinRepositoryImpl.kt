package com.android.aop.part2.toycoininformation.data.repository

import com.android.aop.part2.toycoininformation.data.remote.CoinPaprikaApi
import com.android.aop.part2.toycoininformation.data.remote.dto.CoinDetailDto
import com.android.aop.part2.toycoininformation.data.remote.dto.CoinDto
import com.android.aop.part2.toycoininformation.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val coinPaprikaApi: CoinPaprikaApi
) : CoinRepository {

    override suspend fun getCoins(): List<CoinDto> {
        return coinPaprikaApi.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return coinPaprikaApi.getCoinById(coinId = coinId)
    }
}