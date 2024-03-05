package com.example.solutionx.domain.model

data class Filter(
    val id: Int,
    val name: String
): DisplayableItem {
    override fun getItemType(): Int {
        return ItemType.FILTER.ordinal
    }
}