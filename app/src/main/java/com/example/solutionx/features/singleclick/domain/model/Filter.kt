package com.example.solutionx.features.singleclick.domain.model

import com.example.solutionx.features.singleclick.domain.SingleItem

//@Parcelize
data class Filter(
    override val id: Int,
    override val name: String,
    override var selected: Boolean,
): SingleItem//, Parcelable
