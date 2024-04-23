package com.example.solutionx.features.savelist.presentation.ui.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.solutionx.R
import com.example.solutionx.android.helpers.logging.LoggerFactory
import com.example.solutionx.databinding.ActivityList2Binding
import com.example.solutionx.features.savelist.domain.interactor.ListUpdateWorker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListActivity : AppCompatActivity() {

    private val viewModel: MainListViewModel by viewModels()
    private lateinit var binding: ActivityList2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityList2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupButtons()
        observeState()
    }

    private fun  observeState() {
        lifecycleScope.launch {
            viewModel.state .collect { state ->
                when (state) {
                    is MainListState.Loading -> { }
                    is MainListState.Success -> {
                        val data = state.data
                        for (name in data) {
                            logger.info("workInfo: $name")
                        }
                    }
                    is MainListState.Error -> {
                        val error = state.error
                        logger.error("Error: ",error)
                    }
                    is MainListState.IdleState -> { }
                }
            }
        }
    }


    private fun setupButtons() {
        binding.setNamesButton.setOnClickListener {
            val intent = MainListIntent.SaveNamesIntent(listOf("mahmoud", "Ali","Mostafa"))
            viewModel.handleIntent(intent)
        }
        binding.updateListButton.setOnClickListener {
            val intent = MainListIntent.UpdateNamesListIntent(listOf("مصطفى", "على","محمود"))
            viewModel.handleIntent(intent)
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(ListActivity::class.java)
    }
}