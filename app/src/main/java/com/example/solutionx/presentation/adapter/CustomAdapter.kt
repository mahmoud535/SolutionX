package com.example.solutionx.presentation.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.solutionx.R
import com.example.solutionx.databinding.ListItemsBinding
import com.example.solutionx.domain.model.Countries
import com.example.solutionx.domain.model.Currencies
import com.example.solutionx.domain.model.DisplayableItem
import com.example.solutionx.domain.model.Filter
import com.example.solutionx.domain.model.ItemType

class CustomAdapter(
    private val itemList: List<DisplayableItem>,
    private val onItemClickListener: (DisplayableItem) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var binding: ListItemsBinding
    private var isItemClicked = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ItemType.COUNTRIES.ordinal -> {
                 binding =
                    ListItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                CountriesViewHolder(binding)
            }

            ItemType.CURRENCIES.ordinal -> {
                 binding =
                    ListItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                CurrenciesViewHolder(binding)
            }

            ItemType.FILTER.ordinal -> {
                 binding =
                    ListItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                FilterViewHolder(binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = itemList[position]
        when (holder) {
            is CountriesViewHolder -> holder.bind(item as Countries)
            is CurrenciesViewHolder -> holder.bind(item as Currencies)
            is FilterViewHolder -> holder.bind(item as Filter)
        }
    }

    override fun getItemCount(): Int = itemList.size


    override fun getItemViewType(position: Int): Int {
        return itemList[position].getItemType()
    }

    inner class CountriesViewHolder(private val binding: ListItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(country: Countries) {
            binding.tvName.text = country.name
            binding.tvCurrency.text = country.currency
            binding.tvCode.text = country.code
            binding.tvFlag.text = country.flag
            binding.tvPhoneCode.text = country.phone_code

            updateItemState()

            binding.root.setOnClickListener {
                isItemClicked = !isItemClicked
                onItemClickListener(country)
                updateItemState()
            }
        }
        private fun updateItemState() {
            if (isItemClicked) {
                binding.tvName.setTextColor(ContextCompat.getColor(binding.root.context, R.color.green))
                binding.tvCode.setTextColor(ContextCompat.getColor(binding.root.context, R.color.green))
                binding.tvFlag.setTextColor(ContextCompat.getColor(binding.root.context, R.color.green))
                binding.tvCurrency.setTextColor(ContextCompat.getColor(binding.root.context, R.color.green))
                binding.tvPhoneCode.setTextColor(ContextCompat.getColor(binding.root.context, R.color.green))
                binding.ivCheck.visibility = View.VISIBLE
            } else {
                binding.tvName.setTextColor(ContextCompat.getColor(binding.root.context, R.color.black))
                binding.tvCode.setTextColor(ContextCompat.getColor(binding.root.context, R.color.black))
                binding.tvFlag.setTextColor(ContextCompat.getColor(binding.root.context, R.color.black))
                binding.tvCurrency.setTextColor(ContextCompat.getColor(binding.root.context, R.color.black))
                binding.tvPhoneCode.setTextColor(ContextCompat.getColor(binding.root.context, R.color.black))
                binding.ivCheck.visibility = View.GONE
            }
        }
    }

    inner class CurrenciesViewHolder(private val binding: ListItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(currency: Currencies) {
            // Bind currency data
            binding.tvName.text = currency.name
            binding.tvCode.text = currency.code
            binding.tvFlag.text = currency.sign
            binding.tvCurrency.visibility = View.GONE
            binding.tvPhoneCode.visibility = View.GONE

            updateItemState()
            binding.root.setOnClickListener {
                isItemClicked = !isItemClicked
                onItemClickListener(currency)
                updateItemState()
            }
        }
    }

    inner class FilterViewHolder(private val binding: ListItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(filter: Filter) {
            binding.tvName.text = filter.name
            binding.tvCurrency.visibility = View.GONE
            binding.tvCode.visibility = View.GONE
            binding.tvFlag.visibility = View.GONE
            binding.tvPhoneCode.visibility = View.GONE

            updateItemState()

            binding.root.setOnClickListener {
                isItemClicked = !isItemClicked
                onItemClickListener(filter)
                updateItemState()
            }
        }
    }

     fun updateItemState() {
        if (isItemClicked) {
            binding.tvName.setTextColor(ContextCompat.getColor(binding.root.context, R.color.green))
            binding.tvCode.setTextColor(ContextCompat.getColor(binding.root.context, R.color.green))
            binding.tvFlag.setTextColor(ContextCompat.getColor(binding.root.context, R.color.green))
            binding.tvCurrency.setTextColor(ContextCompat.getColor(binding.root.context, R.color.green))
            binding.tvPhoneCode.setTextColor(ContextCompat.getColor(binding.root.context, R.color.green))
            binding.ivCheck.visibility = View.VISIBLE
        } else {
            binding.tvName.setTextColor(ContextCompat.getColor(binding.root.context, R.color.black))
            binding.tvCode.setTextColor(ContextCompat.getColor(binding.root.context, R.color.black))
            binding.tvFlag.setTextColor(ContextCompat.getColor(binding.root.context, R.color.black))
            binding.tvCurrency.setTextColor(ContextCompat.getColor(binding.root.context, R.color.black))
            binding.tvPhoneCode.setTextColor(ContextCompat.getColor(binding.root.context, R.color.black))
            binding.ivCheck.visibility = View.GONE
        }
    }
}