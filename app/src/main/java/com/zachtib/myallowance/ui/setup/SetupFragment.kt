package com.zachtib.myallowance.ui.setup

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
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

        viewModel.getState().observeWith { state: SetupViewState ->
            if (state.finished) {
                findNavController().popBackStack()
            } else {
                authorizeButton.isEnabled = state.authorizedButtonEnabled
                saveButton.isEnabled = state.saveButtonEnabled

                budgetSpinner.onItemSelectedListener = null
                categorySpinner.onItemSelectedListener = null

                if (state.budgets.isNotEmpty()) {
                    Timber.d("Got ${state.budgets.size} budgets")
                    val adapter = ArrayAdapter(requireContext(),
                        android.R.layout.simple_spinner_dropdown_item,
                        state.budgets.map { budget -> budget.name })
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    budgetSpinner.adapter = adapter
                    if (state.selectedBudgetIndex != -1) {
                        budgetSpinner.setSelection(state.selectedBudgetIndex)
                    }
                    budgetSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onNothingSelected(parent: AdapterView<*>?) {
                            if (state.selectedBudgetIndex != -1) {
                                viewModel.onBudgetSelectionCleared()
                            }
                        }

                        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                            if (state.selectedBudgetIndex != position) {
                                launch {
                                    viewModel.onBudgetItemSelected(position)
                                }
                            }
                        }
                    }
                }
                if (state.categories.isNotEmpty()) {
                    Timber.d("Got ${state.categories.size} category groups")
                    val adapter = ArrayAdapter(requireContext(),
                        android.R.layout.simple_spinner_dropdown_item,
                        state.categories.map { item: Either<CategoryGroup, Category> ->
                            when (item) {
                                is Either.Left -> item.value.name
                                is Either.Right -> item.value.name
                            }
                        }
                    )
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    categorySpinner.adapter = adapter
                    if (state.selectedCategoryIndex != -1) {
                        categorySpinner.setSelection(state.selectedCategoryIndex)
                    }
                    categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onNothingSelected(parent: AdapterView<*>?) {
                            if (state.selectedCategoryIndex != -1) {
                                viewModel.onCategorySelectionCleared()
                            }
                        }

                        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                            if (state.selectedCategoryIndex != position) {
                                viewModel.onCategorySelected(position)
                            }
                        }
                    }
                }
            }
        }

        developerTokenField.onTextChanged { viewModel.developerToken = it }

        authorizeButton.setOnClickListener {
            launch {
                viewModel.onAuthorizedClicked()
            }
        }

        saveButton.setOnClickListener { viewModel.onSaveButtonPressed() }
    }
}