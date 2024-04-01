package com.example.solutionx.features.services.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.solutionx.databinding.ListItemsBinding

@SuppressLint("NotifyDataSetChanged")
class CustomAdapter(
    private val onItemClickListener: OnListItemClick? = null,
) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    private lateinit var binding: ListItemsBinding
    private var itemsList: MutableList<SingleSelection> = ArrayList()
    private var checkedPosition = -1

    fun setItems(items: List<SingleSelection>) {
        val diffCallback = SingleItemDiffCallback(items, items)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        itemsList.clear()
        itemsList.addAll(items)
        diffResult.dispatchUpdatesTo(this)

//        this.items = items as MutableList<SingleSelection>
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ListItemsBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        init {
            binding.root.setOnClickListener(this)
        }

        fun bind(item: SingleSelection) = binding.apply {
            val isItemSelected = checkedPosition == bindingAdapterPosition
            tvName.text = item.name
            root.isSelected = isItemSelected
            ivCheck.visibility = if (isItemSelected)
                View.VISIBLE
            else
                View.GONE
        }
        override fun onClick(p0: View?) {
            applySelector(itemsList[bindingAdapterPosition])
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ListItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemsList[position])
    }

    override fun getItemCount(): Int = itemsList.size

    private fun applySelector(item: SingleSelection) {
        if (itemsList.contains(item)) {
            val position = itemsList.indexOf(item)
            if (checkedPosition != position) {
                checkedPosition = position
                notifyDataSetChanged()
                onItemClickListener?.onItemClick(item)
            }
        }
    }

    private class SingleItemDiffCallback(
        private val oldList: List<SingleSelection>,
        private val newList: List<SingleSelection>
    ) : DiffUtil.Callback(){
        override fun getOldListSize(): Int {
            return oldList.size
        }
        override fun getNewListSize(): Int {
            return newList.size
        }
        override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldList[oldItem].id == newList[newItem].id // assuming SingleSelection has an id field
        }
        override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldList[oldItem] == newList[newItem]
        }

    }
}