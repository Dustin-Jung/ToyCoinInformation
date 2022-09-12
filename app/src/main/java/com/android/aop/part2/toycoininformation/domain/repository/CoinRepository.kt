package com.android.aop.part2.toycoininformation.domain.repository

import com.android.aop.part2.toycoininformation.data.remote.dto.CoinDetailDto
import com.android.aop.part2.toycoininformation.data.remote.dto.CoinDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinDetailDto
}