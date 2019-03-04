package com.zachtib.myallowance.ui.main

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.zachtib.android.BaseFragment
import com.zachtib.myallowance.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment(R.layout.fragment_main) {
    private val viewModel: MainViewModel by viewModel()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (viewModel.shouldRedirectToSetup()) {
            findNavController().navigate(R.id.action_mainFragment_to_setupFragment)
            return
        }
    }
}