package com.example.solutionx.features.savelist.presentation.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.ListFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.solutionx.R
import com.example.solutionx.android.helpers.logging.LoggerFactory
import com.example.solutionx.common.data.model.exception.LeonException
import com.example.solutionx.common.presentation.fragment.BaseFragment
import com.example.solutionx.databinding.FragmentListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListFragment : BaseFragment<FragmentListBinding>(
     FragmentListBinding::inflate
) {
    private val viewModel: MainListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        setUpActions()
        return binding.root
    }

    override fun setUpActions() {
        setUpObservers()
        setUpListeners()
    }

    override fun setUpObservers() {
        lifecycleScope.launch {
            viewModel.viewState.collect { state ->
                when (state) {
                    is MainListState.Success -> {
                        val data = state.data
                        logger.info("workInfo: $data")
                    }
                    is MainListState.Loading -> showProgressBar(resources.getString(R.string.please_wait))
                    is MainListState.Error -> {
                        handleException(LeonException.Unknown(state.error.message))
                        val error = state.error
                        logger.error("Error: ", error)
                    }
                    else -> {
//                        handleException(LeonException.Unknown("Unknown Error"))
                    }
                }
            }
        }
    }

    override fun setUpListeners() {
        binding.setNamesButton.setOnClickListener {
            saveNames()
        }
        binding.updateListButton.setOnClickListener {
            updateNamesList()
        }
    }

    private fun saveNames() {
        val intent = MainListIntent.SaveNamesIntent(
            listOf("mahmoud", "Ali", "Mostafa", "Kareem", "Ahmed")
        )
        viewModel.handleIntent(intent)
    }

    private fun updateNamesList() {
        val intent = MainListIntent.UpdateNamesListIntent(
            listOf("مصطفى ", "كريم", "احمد", "على", "محمود")
        )
        viewModel.handleIntent(intent)
    }

    override fun setUpRecyclerView() { }

    override fun onRefresh() { }

    companion object {
        private val logger = LoggerFactory.getLogger(ListFragment::class.java)
    }
}