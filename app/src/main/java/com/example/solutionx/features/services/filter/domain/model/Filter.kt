package com.example.solutionx.features.services.filter.domain.model

import android.os.Parcelable
import com.example.solutionx.features.services.presentation.adapter.SingleSelection
import kotlinx.parcelize.Parcelize

@Parcelize
data class Filter(
    override var id: Int,
    override var name: String,
    override var selected: Boolean
) : SingleSelection, Parcelable