package com.android.aop.part2.toycoininformation.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.android.aop.part2.toycoininformation.R
import com.android.aop.part2.toycoininformation.databinding.ActivityMainBinding
import com.android.aop.part2.toycoininformation.presentation.base.BaseActivity
import com.android.aop.part2.toycoininformation.presentation.detail.CoinDetailFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()
    }

    private fun initViewModel(){
        mainViewModel.mainViewStateLiveData.observe(this) { viewState ->
            when (viewState) {

                is MainViewState.RouteDetail -> {

                    supportFragmentManager.beginTransaction()
                        .replace(binding.container.id, CoinDetailFragment.newInstance(viewState.item.id))
                        .addToBackStack(null).commit()

                }

                is MainViewState.Error -> {
                    Toast.makeText(this, viewState.message, Toast.LENGTH_LONG).show()
                }
            }
        }

    }
}