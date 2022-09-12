package com.android.aop.part2.toycoininformation.presentation.list

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.android.aop.part2.toycoininformation.R
import com.android.aop.part2.toycoininformation.databinding.FragmentCoinlistBinding
import com.android.aop.part2.toycoininformation.presentation.adapter.CoinAdapter
import com.android.aop.part2.toycoininformation.presentation.base.BaseFragment
import com.android.aop.part2.toycoininformation.presentation.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinListFragment : BaseFragment<FragmentCoinlistBinding>(R.layout.fragment_coinlist) {

    private val coinListViewModel by viewModels<CoinListViewModel>()

    private val mainViewModel by activityViewModels<MainViewModel>()

    private val coinAdapter = CoinAdapter { clickItem ->
        mainViewModel.routeDetail(clickItem)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        initViewModel()
    }

    private fun init() {

        with(binding) {

            recyclerView.adapter = coinAdapter

            searchEditText.setOnClickListener {
                coinListViewModel.coinSearchLiveData
            }
        }

    }

    private fun initViewModel() {
        binding.viewModel = coinListViewModel

        coinListViewModel.viewStateLiveData.observe(viewLifecycleOwner) { viewState ->
            (viewState as? CoinListViewState)?.let {
                onChangedCoinListViewState(it)
            }
        }
    }

    private fun onChangedCoinListViewState(viewState: CoinListViewState) {
        when (viewState) {
            is CoinListViewState.GetCoinList -> {
                coinAdapter.addAll(viewState.list)
            }

            is CoinListViewState.Error -> {
                Toast.makeText(requireContext(), viewState.errorMessage, Toast.LENGTH_SHORT).show()
            }

            is CoinListViewState.Loading -> {
                binding.progressbar.bringToFront()
                binding.progressbar.isVisible = viewState.isLoading
            }
        }
    }
}