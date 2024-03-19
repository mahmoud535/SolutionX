package com.example.solutionx.features.singleclick.presentation.adapter

import com.example.solutionx.features.singleclick.domain.SingleItem
import java.io.Serializable

interface OnListItemClick : Serializable {
    fun onItemClick(singleItem: SingleItem)
}