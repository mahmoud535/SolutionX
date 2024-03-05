package com.example.solutionx.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.solutionx.presentation.adapter.CustomAdapter
import com.example.solutionx.databinding.ActivityListBinding
import com.example.solutionx.domain.DataModel
import com.example.solutionx.domain.model.Countries
import com.example.solutionx.domain.model.Currencies
import com.example.solutionx.domain.model.DisplayableItem
import com.example.solutionx.domain.model.Filter

class ListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListBinding

    private val customAdapter:CustomAdapter by lazy {
        CustomAdapter(itemList = DataModel.countryList,
            onItemClickListener = { item -> checkInClickItem(item) })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerViews()

    }
    private fun setupRecyclerViews() {
        binding.rvCountry.apply {
            layoutManager = LinearLayoutManager(this@ListActivity)
            adapter = customAdapter
        }
    }
    private fun checkInClickItem(item:DisplayableItem){
        when (item) {
            is Currencies -> handleCurrencyClick(item)
            is Countries -> handleCountryClick(item)
            is Filter -> handleFilterClick(item)
        }
    }
    private fun handleCurrencyClick(currency: Currencies) {
        Toast.makeText(this, "this is Currency Items", Toast.LENGTH_SHORT).show()
    }
    private fun handleCountryClick(country: Countries) {
        Toast.makeText(this, "this is Country Items", Toast.LENGTH_SHORT).show()
    }
    private fun handleFilterClick(filter: Filter) {
        Toast.makeText(this, "this is Filter Items", Toast.LENGTH_SHORT).show()
    }
}