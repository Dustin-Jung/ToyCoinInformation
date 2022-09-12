package com.android.aop.part2.toycoininformation.domain.use_case.get_coins

import com.android.aop.part2.toycoininformation.data.remote.dto.toCoinDetail
import com.android.aop.part2.toycoininformation.domain.model.CoinDetail
import com.android.aop.part2.toycoininformation.domain.repository.CoinRepository
import com.android.aop.part2.toycoininformation.ext.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val coinRepository: CoinRepository
) {
    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading<CoinDetail>())
            val coin = coinRepository.getCoinById(coinId = coinId).toCoinDetail()
            emit(Resource.Success<CoinDetail>(coin))
        } catch (e: HttpException) {
            emit(Resource.Error<CoinDetail>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<CoinDetail>("Couldn't reach server. Check your internet connection."))
        }
    }
}