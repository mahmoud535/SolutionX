package com.example.solutionx.features.services.presentation.ui.fragment.list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.solutionx.databinding.FragmentList2Binding
import com.example.solutionx.features.services.country.domain.models.Country
import com.example.solutionx.features.services.presentation.adapter.CustomAdapter
import com.example.solutionx.features.services.presentation.adapter.OnListItemClick
import com.example.solutionx.features.services.presentation.adapter.SingleSelection
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment2 : Fragment(), OnListItemClick {

    private lateinit var binding: FragmentList2Binding
    private val customAdapter: CustomAdapter by lazy {
        CustomAdapter(this)
    }
    private val loginVM: ListVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentList2Binding.inflate(layoutInflater)

        lifecycleScope.launchWhenCreated {
            loginVM.viewState.collect {
                renderViewState(it)
            }
        }
        loginVM.processIntent(SingleClickIntent.FetchData)

        setupRecyclerViews()

        return binding.root
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
                Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()

            }
        }
    }


    private fun setupRecyclerViews() {
        binding.recyclerSingleSelection.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = customAdapter
//            customAdapter.setItems(DataModel.getCountries(this@ListActivity))
        }
    }

    override fun onItemClick(selectedItem: SingleSelection) {
        Log.i("ListActivity", "onSingleItemSelected: $selectedItem")
        Toast.makeText(requireContext(), selectedItem.name, Toast.LENGTH_SHORT).show()

    }

}