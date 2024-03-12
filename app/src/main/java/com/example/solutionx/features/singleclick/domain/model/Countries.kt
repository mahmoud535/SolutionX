package com.example.solutionx.features.singleclick.domain.model

import com.example.solutionx.features.singleclick.presentation.ui.activity.list.SingleItem

//@Parcelize
data class Countries(
    val code: String,
    val currency: String,
    val flag: String,
    override val id: Int,
    override val name: String,
    val phone_code: String,
    override var selected: Boolean,
): SingleItem//, Parcelable