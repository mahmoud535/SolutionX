package com.example.solutionx.features.singleclick.domain.model

import com.example.solutionx.features.singleclick.presentation.ui.activity.list.SingleItem

//@Parcelize
data class Currencies(
    val code: String,
    override val id: Int,
    override val name: String,
    val sign: String,
    override var selected: Boolean,
): SingleItem//, Parcelable
