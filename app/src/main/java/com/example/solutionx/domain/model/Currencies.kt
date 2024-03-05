package com.example.solutionx.domain.model

data class Currencies(
    val code: String,
    val id: Int,
    val name: String,
    val sign: String
): DisplayableItem {
    override fun getItemType(): Int {
        return ItemType.CURRENCIES.ordinal
    }
}