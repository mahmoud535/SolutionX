package com.example.solutionx.domain.model


data class Countries(
    val code: String,
    val currency: String,
    val flag: String,
    val id: Int,
    val name: String,
    val phone_code: String
):DisplayableItem{
    override fun getItemType(): Int {
        return ItemType.COUNTRIES.ordinal
    }
}