package com.android.aop.part2.toycoininformation.domain.use_case.get_coin

import com.android.aop.part2.toycoininformation.data.remote.dto.toCoin
import com.android.aop.part2.toycoininformation.domain.model.Coin
import com.android.aop.part2.toycoininformation.domain.repository.CoinRepository
import com.android.aop.part2.toycoininformation.ext.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val coinRepository: CoinRepository
) {
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading<List<Coin>>())
            val coins = coinRepository.getCoins().map { it.toCoin() }
            emit(Resource.Success<List<Coin>>(coins))
        } catch (e: HttpException) {
            emit(Resource.Error<List<Coin>>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<List<Coin>>("Couldn't reach server. Check your internet connection."))
        }
    }
}