package com.example.solutionx.features.singleclick.presentation.ui.activity.list

import com.example.solutionx.features.singleclick.domain.model.Countries
import com.example.solutionx.features.singleclick.domain.model.Currencies
import com.example.solutionx.features.singleclick.domain.model.Filter

data class MainUiState(
    val currency: List<CurrencyUiState> = emptyList(),
    val country: List<CountryUiState> = emptyList(),
    val filter: List<FilterUiState> = emptyList(),
    val model: List<Model> = emptyList()
)


data class Model(
    override val id: Int = 0,
    override val name: String = " ",
    override val isSelected: Boolean = false

) : AdapterModel

data class CountryUiState(
    override val id: Int = 0,
    override val name: String = " ",
    val currency: String = " ",
    val code: String = " ",
    val phone_code: String = " ",
    val flag: String = " ",
    override val isSelected: Boolean,
) : AdapterModel

data class CurrencyUiState(
    override val id: Int = 0,
    override val name: String = " ",
    val sign: String = "",
    val code: String = "",
    override val isSelected: Boolean,
) : AdapterModel

data class FilterUiState(
    override val id: Int = 0,
    override val name: String = "",
    override val isSelected: Boolean,
) : AdapterModel

interface AdapterModel {
    val id: Int
    val name: String
    val isSelected: Boolean
}

fun List<Filter>.toFilterUiState(): List<FilterUiState> {
    return map {
        FilterUiState(
            id = it.id,
            name = it.name,
            isSelected = false,

            )
    }
}

fun List<Countries>.toCountryUiState(): List<CountryUiState> {
    return map {
        CountryUiState(
            id = it.id,
            name = it.name,
            currency = it.currency,
            code = it.code,
            phone_code = it.phone_code,
            flag = it.flag,
            isSelected = false,
        )
    }
}

fun List<Currencies>.toCurrencyUiState(): List<CurrencyUiState> {
    return map {
        CurrencyUiState(
            id = it.id,
            name = it.name,
            code = it.code,
            isSelected = false,
        )
    }
}
fun List<AdapterModel>.toModelList(): List<Model> {
    return map {
        Model(
            id = it.id,
            name = it.name,
            isSelected = it.isSelected,
        )
    }
}
fun AdapterModel.toModel() = Model(id = id, name = name, isSelected = isSelected)
