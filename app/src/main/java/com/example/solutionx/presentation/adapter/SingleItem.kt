package com.example.solutionx.presentation.adapter

interface SingleItem {
    val id: Int
    val name: String
    var selected: Boolean
    fun getIconRes(): Int = -1
}