package com.example.solutionx.features.services.presentation.adapter

import java.io.Serializable

interface OnListItemClick : Serializable {
    fun onItemClick(singleSelection: SingleSelection)
}