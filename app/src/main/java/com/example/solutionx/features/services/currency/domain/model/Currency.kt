package com.example.solutionx.features.services.currency.domain.model

import android.os.Parcelable
import com.example.solutionx.features.services.presentation.adapter.SingleSelection
import kotlinx.parcelize.Parcelize

@Parcelize
data class Currency(
    override var id: Int,
    override var name: String,
    val sign: String,
    val code: String,
    override var selected: Boolean
) : SingleSelection, Parcelable