package com.example.solutionx.features.singleclick.domain

interface SingleItem {
    val id: Int
    val name: String
    var selected: Boolean
    fun getIconRes(): Int = -1
}