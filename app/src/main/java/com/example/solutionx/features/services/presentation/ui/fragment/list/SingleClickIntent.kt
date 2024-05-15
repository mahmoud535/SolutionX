package com.example.solutionx.features.services.presentation.ui.fragment.list


sealed class SingleClickIntent {

    object FetchData : SingleClickIntent()
//    data class getCurrency(val phone: String, val password: String) : SingleClickIntent()
//    data class getFilter(val token: String) : SingleClickIntent()
}