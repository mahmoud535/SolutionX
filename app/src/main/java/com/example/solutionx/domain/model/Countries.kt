package com.example.solutionx.domain.model

import android.os.Parcelable
import com.example.solutionx.presentation.adapter.SingleItem

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