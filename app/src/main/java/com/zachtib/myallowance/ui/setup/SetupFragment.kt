package com.zachtib.myallowance.ui.setup

import android.os.Bundle
import com.zachtib.android.BaseFragment
import com.zachtib.android.textValue
import com.zachtib.myallowance.R
import kotlinx.android.synthetic.main.fragment_setup.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class SetupFragment : BaseFragment(R.layout.fragment_setup) {
    private val viewModel: SetupViewModel by viewModel()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        authorizeButton.setOnClickListener {
            launch {
                val token = developerTokenField.textValue.toString()
                viewModel.onAuthorizedClicked(token)
                val budgets = viewModel.getBudgets()
                Timber.d("Got ${budgets.size} budgets")
            }
        }
    }
}