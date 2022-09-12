package com.android.aop.part2.toycoininformation.presentation.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.android.aop.part2.toycoininformation.R
import com.android.aop.part2.toycoininformation.databinding.FragmentCoindetailBinding
import com.android.aop.part2.toycoininformation.presentation.base.BaseFragment
import com.android.aop.part2.toycoininformation.presentation.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinDetailFragment : BaseFragment<FragmentCoindetailBinding>(R.layout.fragment_coindetail) {

    private val coinDetailViewModel by viewModels<CoinDetailViewModel>()

    private val mainViewModel by activityViewModels<MainViewModel>()

    private var clickCoinId: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        clickCoinId = arguments?.getString(KEY_COIN_ID)
        super.onCreate(savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        initViewModel()
    }


    private fun initUi() {

    }

    private fun initViewModel() {
        binding.viewModel = coinDetailViewModel
        coinDetailViewModel.getDetailList(coinId = clickCoinId)

        coinDetailViewModel.viewStateLiveData.observe(viewLifecycleOwner) { viewState ->
            (viewState as? CoinDetailViewState)?.let {
                onChangedCoinDetailViewState(it)
            }
        }
    }

    private fun onChangedCoinDetailViewState(viewState: CoinDetailViewState) {
        when (viewState) {

            is CoinDetailViewState.RouteCoinList -> {
                requireActivity().onBackPressed()
            }

            is CoinDetailViewState.GetCoinDetailList -> {

            }

            is CoinDetailViewState.Error -> {
                Toast.makeText(requireContext(), viewState.errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }


    companion object {

        private const val KEY_COIN_ID = "key_coin_id"

        fun newInstance(id: String): CoinDetailFragment {
            return CoinDetailFragment().apply {
                arguments = bundleOf(Pair(KEY_COIN_ID, id))
            }
        }
    }
}