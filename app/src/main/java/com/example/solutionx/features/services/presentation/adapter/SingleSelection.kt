package com.example.solutionx.features.services.presentation.adapter

interface SingleSelection {
    val id: Int
    val name: String
    var selected: Boolean
    fun getIconRes(): Int = -1
}