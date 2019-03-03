package com.zachtib.myallowance.ui.main

import com.zachtib.android.BaseFragment
import com.zachtib.myallowance.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment(R.layout.fragment_main) {
    private val viewModel: MainViewModel by viewModel()
}