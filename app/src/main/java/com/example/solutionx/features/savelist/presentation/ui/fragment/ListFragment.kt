package com.example.solutionx.features.savelist.presentation.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.ListFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.solutionx.android.helpers.logging.LoggerFactory
import com.example.solutionx.databinding.FragmentListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListFragment : Fragment() {
    private val viewModel: MainListViewModel by viewModels()
    lateinit var binding: FragmentListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(layoutInflater)

        setupButtons()
        observeState()
        return binding.root
    }


    private fun observeState() {
        lifecycleScope.launch {
            viewModel.viewState.collect { state ->
                when (state) {
                    is MainListState.Success -> {
                        val data = state.data
                        logger.info("workInfo: $data")
                    }

                    is MainListState.Error -> {
                        val error = state.error
                        logger.error("Error: ", error)
                    }
                    else -> {

                    }
                }
            }
        }
    }

    private fun setupButtons() {
        binding.setNamesButton.setOnClickListener {
            val intent = MainListIntent.SaveNamesIntent(
                listOf(
                    "mahmoud",
                    "Ali",
                    "Mostafa",
                    "Kareem",
                    "Ahmed"
                )
            )
            viewModel.handleIntent(intent)
        }
        binding.updateListButton.setOnClickListener {
            val intent = MainListIntent.UpdateNamesListIntent(
                listOf(
                    "مصطفى ",
                    "كريم",
                    "احمد",
                    "على",
                    "محمود"
                )
            )
            viewModel.handleIntent(intent)
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(ListFragment::class.java)
    }
}