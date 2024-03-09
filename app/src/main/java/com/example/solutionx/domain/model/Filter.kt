package com.example.solutionx.domain.model

import android.os.Parcelable
import com.example.solutionx.presentation.adapter.SingleItem

//@Parcelize
data class Filter(
    override val id: Int,
    override val name: String,
    override var selected: Boolean,
): SingleItem//, Parcelable
