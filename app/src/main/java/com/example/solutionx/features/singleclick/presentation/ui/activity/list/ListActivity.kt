package com.example.solutionx.features.singleclick.presentation.ui.activity.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.solutionx.R
import com.example.solutionx.databinding.ActivityListBinding
import com.example.solutionx.features.singleclick.domain.model.DataModel

class ListActivity : AppCompatActivity(), OnListItemClick {
    private lateinit var binding: ActivityListBinding
    private val customAdapter: CustomAdapter by lazy {
        CustomAdapter(this)
    }
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

        setupRecyclerViews()
    }
    private fun setupRecyclerViews() {
        binding.recyclerSingleSelection.apply {
            layoutManager = LinearLayoutManager(this@ListActivity)
            adapter = customAdapter
            customAdapter.setItems(DataModel.getCountries(this@ListActivity))
        }
    }
    override fun onItemClick(selectedItem: SingleItem) {
        Log.i("ListActivity", "onSingleItemSelected: $selectedItem")
        Toast.makeText(this, selectedItem.name, Toast.LENGTH_SHORT).show()

    }
}