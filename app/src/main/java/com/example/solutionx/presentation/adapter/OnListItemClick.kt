package com.example.solutionx.presentation.adapter

import java.io.Serializable

interface OnListItemClick : Serializable {
    fun onItemClick(singleItem: SingleItem)
}