package com.example.solutionx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.solutionx.adapter.CustomAdapter
import com.example.solutionx.databinding.ActivityListBinding
import com.example.solutionx.model.Countries
import com.example.solutionx.model.Currencies
import com.example.solutionx.model.Filter

class ListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerViews()

    }

    private fun setupRecyclerViews(){
        binding.rvCountry.apply {
            layoutManager = LinearLayoutManager(this@ListActivity)
            adapter = adapter2
        }
        binding.rvCurrencies.apply {
            layoutManager = LinearLayoutManager(this@ListActivity)
            adapter = adapter1
        }
        binding.rvFilter.apply {
            layoutManager = LinearLayoutManager(this@ListActivity)
            adapter = adapter3
        }
    }

    private val adapter1 = CustomAdapter(
        itemList = DataModel.currencyList,
        onItemClickListener = { currency -> handleCurrencyClick() },
    )
    private val adapter2 = CustomAdapter(
        itemList = DataModel.countryList,
        onItemClickListener = { country -> handleCountryClick() },
    )
    private val adapter3 = CustomAdapter(
        itemList = DataModel.filterList,
        onItemClickListener = { filter -> handleFilterClick() }
    )

    private fun handleCurrencyClick() {
        Toast.makeText(this, "this is Currency Items", Toast.LENGTH_SHORT).show()
    }

    private fun handleCountryClick() {
        Toast.makeText(this, "this is Country Items", Toast.LENGTH_SHORT).show()
    }

    private fun handleFilterClick() {
        Toast.makeText(this, "this is Filter Items", Toast.LENGTH_SHORT).show()
    }
}