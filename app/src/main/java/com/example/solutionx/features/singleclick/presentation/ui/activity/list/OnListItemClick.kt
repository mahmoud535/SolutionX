package com.example.solutionx.features.singleclick.presentation.ui.activity.list

import java.io.Serializable

interface OnListItemClick : Serializable {
    fun onItemClick(singleItem: SingleItem)
}