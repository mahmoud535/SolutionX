package com.example.solutionx.features.services.presentation.ui.activity.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.solutionx.R
import com.example.solutionx.databinding.ActivityListBinding
import com.example.solutionx.features.services.country.domain.models.Country
import com.example.solutionx.features.services.presentation.adapter.CustomAdapter
import com.example.solutionx.features.services.presentation.adapter.OnListItemClick
import com.example.solutionx.features.services.presentation.adapter.SingleSelection
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ListActivity : AppCompatActivity(), OnListItemClick {
    private lateinit var binding: ActivityListBinding
    private val customAdapter: CustomAdapter by lazy {
        CustomAdapter(this)
    }
    private val loginVM: ListVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.list)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
            lifecycleScope.launchWhenCreated {
                loginVM.viewState.collect {
                    renderViewState(it)
                }
            }
        loginVM.processIntent(SingleClickIntent.FetchData)

        setupRecyclerViews()
    }

    private fun renderViewState(viewState: SingleClickViewState) {
        when (viewState) {
            is SingleClickViewState.Loading -> {
                // Show loading indicator
            }
            is SingleClickViewState.Success -> {
                val countries: List<Country> = viewState.countries
                customAdapter.setItems(countries)
            }
            is SingleClickViewState.Error -> {
                // Handle error state
                val errorMessage = viewState.message
                // Show error message to the user
                Toast.makeText(this@ListActivity, errorMessage, Toast.LENGTH_SHORT).show()

            }
        }
    }


    private fun setupRecyclerViews() {
        binding.recyclerSingleSelection.apply {
            layoutManager = LinearLayoutManager(this@ListActivity)
            adapter = customAdapter
//            customAdapter.setItems(DataModel.getCountries(this@ListActivity))
        }
    }
    override fun onItemClick(selectedItem: SingleSelection) {
        Log.i("ListActivity", "onSingleItemSelected: $selectedItem")
        Toast.makeText(this, selectedItem.name, Toast.LENGTH_SHORT).show()

    }
}