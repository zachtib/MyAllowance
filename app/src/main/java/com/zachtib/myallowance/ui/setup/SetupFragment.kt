package com.zachtib.myallowance.ui.setup

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.zachtib.android.BaseFragment
import com.zachtib.android.onTextChanged
import com.zachtib.android.textValue
import com.zachtib.myallowance.Either
import com.zachtib.myallowance.R
import com.zachtib.myallowance.models.Category
import com.zachtib.myallowance.models.CategoryGroup
import com.zachtib.myallowance.unfurl
import kotlinx.android.synthetic.main.fragment_setup.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class SetupFragment : BaseFragment(R.layout.fragment_setup) {
    private val viewModel: SetupViewModel by viewModel()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.getState().observeWith {
            authorizeButton.isEnabled = it.authorizedButtonEnabled
            if (it.budgets.isNotEmpty()) {
                Timber.d("Got ${it.budgets.size} budgets")
                val adapter = ArrayAdapter(requireContext(),
                    android.R.layout.simple_spinner_dropdown_item,
                    it.budgets.map { budget -> budget.name })
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                budgetSpinner.adapter = adapter

            }
            if (it.categories.isNotEmpty()) {
                Timber.d("Got ${it.categories.size} category groups")
                val adapter = ArrayAdapter(requireContext(),
                    android.R.layout.simple_spinner_dropdown_item,
                    it.categories.map { item: Either<CategoryGroup, Category> ->
                        when (item) {
                            is Either.Left -> item.value.name
                            is Either.Right -> item.value.name
                        }
                    }
                )
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                categorySpinner.adapter = adapter
            }
        }

        developerTokenField.onTextChanged { viewModel.developerToken = it }

        authorizeButton.setOnClickListener {
            launch {
                viewModel.onAuthorizedClicked()
            }
        }

        budgetSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                viewModel.onBudgetSelectionCleared()
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                launch {
                    viewModel.onBudgetItemSelected(position)
                }
            }
        }

        categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                viewModel.onBudgetSelectionCleared()
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                launch {
                    viewModel.onBudgetItemSelected(position)
                }
            }
        }
    }
}