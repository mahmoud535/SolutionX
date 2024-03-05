package com.example.solutionx.presentation.adapter

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.solutionx.R
import com.example.solutionx.databinding.ListItemsBinding
import com.example.solutionx.domain.model.Countries
import com.example.solutionx.domain.model.DisplayableItem

class CountriesViewHolder(private val binding: ListItemsBinding,private val itemList: List<DisplayableItem>,private val onItemClickListener: (DisplayableItem) -> Unit) :
    RecyclerView.ViewHolder(binding.root) {

    private var selectedItemPosition: Int? = null


    fun bind(country: Countries) {
        binding.tvName.text = country.name
        binding.tvCurrency.text = country.currency
        binding.tvCode.text = country.code
        binding.tvFlag.text = country.flag
        binding.tvPhoneCode.text = country.phone_code
        updateItemState(adapterPosition)

        binding.root.setOnClickListener {
            checkItemClick(adapterPosition)
        }
    }

    fun updateItemState(position: Int) {
        if (position==selectedItemPosition) {
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

    fun checkItemClick(adapterPosition:Int){
        val clickedPosition = adapterPosition
        if (clickedPosition != RecyclerView.NO_POSITION) {
            if (selectedItemPosition == clickedPosition) {
                selectedItemPosition = null
            } else {
                selectedItemPosition = clickedPosition
            }
//            notifyDataSetChanged()
            onItemClickListener(itemList[clickedPosition])
        }
    }
}