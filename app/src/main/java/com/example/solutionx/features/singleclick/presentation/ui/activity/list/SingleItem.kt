package com.example.solutionx.features.singleclick.presentation.ui.activity.list

interface SingleItem {
    val id: Int
    val name: String
    var selected: Boolean
    fun getIconRes(): Int = -1
}