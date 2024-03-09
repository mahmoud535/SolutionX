package com.example.solutionx.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.solutionx.R
import com.example.solutionx.databinding.ListItemsBinding

@SuppressLint("NotifyDataSetChanged")
class CustomAdapter(
    private val onItemClickListener: OnListItemClick ?= null,
) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    private lateinit var binding: ListItemsBinding
    private var items: MutableList<SingleItem> = ArrayList()
    private var checkedPosition = -1

    fun setItems(items: List<SingleItem>) {
        this.items = items as MutableList<SingleItem>
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ListItemsBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        init {
            binding.root.setOnClickListener(this)
        }
        fun bind(item: SingleItem) = binding.apply {
                val isItemSelected = checkedPosition == bindingAdapterPosition
                tvName.text = item.name
            root.isSelected = isItemSelected
            ivCheck.visibility = if (isItemSelected)
                View.VISIBLE
            else
                View.GONE
        }
        override fun onClick(p0: View?) {
            applySelector(items[bindingAdapterPosition])
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
         binding = ListItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }
    override fun getItemCount(): Int = items.size

    private fun applySelector(item: SingleItem) {
        if (items.contains(item)) {
            val position = items.indexOf(item)
            if (checkedPosition != position) {
                checkedPosition = position
                notifyDataSetChanged()
                onItemClickListener?.onItemClick(item)
            }
        }
    }
}