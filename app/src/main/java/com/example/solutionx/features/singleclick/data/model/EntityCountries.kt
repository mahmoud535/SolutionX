package com.example.solutionx.features.singleclick.data.model

import com.example.solutionx.features.singleclick.domain.SingleItem

//@Parcelize
data class EntityCountries(
    val code: String,
    val currency: String,
    val flag: String,
    override val id: Int,
    override val name: String,
    val phone_code: String,
    override var selected: Boolean,
): SingleItem//, Parcelable