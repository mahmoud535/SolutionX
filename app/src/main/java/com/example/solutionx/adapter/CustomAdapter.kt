package com.example.solutionx.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.solutionx.R
import com.example.solutionx.databinding.ListItemsBinding
import com.example.solutionx.model.Countries
import com.example.solutionx.model.Currencies
import com.example.solutionx.model.Filter

class CustomAdapter<T>(
    private val itemList: List<T>,
    private val onItemClickListener: (T) -> Unit
) : RecyclerView.Adapter<CustomAdapter<T>.ViewHolder>() {

    private lateinit var binding: ListItemsBinding
    private var isItemClicked = false

    inner class ViewHolder(private val binding: ListItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: T) {
            when (item) {
                is Currencies -> bindCurrencies(item)
                is Filter -> bindFilter(item)
                is Countries -> bindCountries(item)
            }

            updateItemState()

            binding.root.setOnClickListener {
                isItemClicked = !isItemClicked
                updateItemState()
                onItemClickListener(item)
            }
        }

        private fun updateItemState() {
            if (isItemClicked) {
                binding.tvName.setTextColor(Color.parseColor("#50BC75"))
                binding.tvCode.setTextColor(Color.parseColor("#50BC75"))
                binding.tvFlag.setTextColor(Color.parseColor("#50BC75"))
                binding.tvCurrency.setTextColor(Color.parseColor("#50BC75"))
                binding.tvPhoneCode.setTextColor(Color.parseColor("#50BC75"))
                binding.ivCheck.visibility = View.VISIBLE
            } else {
                binding.tvName.setTextColor(Color.parseColor("#FF000000"))
                binding.tvCode.setTextColor(Color.parseColor("#FF000000"))
                binding.tvFlag.setTextColor(Color.parseColor("#FF000000"))
                binding.tvCurrency.setTextColor(Color.parseColor("#FF000000"))
                binding.tvPhoneCode.setTextColor(Color.parseColor("#FF000000"))
                binding.ivCheck.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ListItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size



    private fun bindCurrencies(currency: Currencies) {
        binding.tvName.text = currency.name
        binding.tvCode.text = currency.code
        binding.tvFlag.text = currency.sign
        binding.tvCurrency.visibility = View.GONE
        binding.tvPhoneCode.visibility = View.GONE
    }

    private fun bindFilter(filter: Filter) {
        binding.tvName.text = filter.name
        binding.tvCurrency.visibility = View.GONE
        binding.tvCode.visibility = View.GONE
        binding.tvFlag.visibility = View.GONE
        binding.tvPhoneCode.visibility = View.GONE
    }

    private fun bindCountries(country: Countries) {
        binding.tvName.text = country.name
        binding.tvCurrency.text = country.currency
        binding.tvCode.text = country.code
        binding.tvFlag.text = country.flag
        binding.tvPhoneCode.text = country.phone_code
    }
}