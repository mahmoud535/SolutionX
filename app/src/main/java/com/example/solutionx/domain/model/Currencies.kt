package com.example.solutionx.domain.model

import android.os.Parcelable
import com.example.solutionx.presentation.adapter.SingleItem

//@Parcelize
data class Currencies(
    val code: String,
    override val id: Int,
    override val name: String,
    val sign: String,
    override var selected: Boolean,
): SingleItem//, Parcelable
