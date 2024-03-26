package com.example.solutionx.features.services.presentation.ui.activity.list

import com.example.solutionx.features.authentication.login.presentation.ui.activity.login.LoginIntent

sealed class SingleClickIntent {

    object FetchData : SingleClickIntent()
//    data class getCurrency(val phone: String, val password: String) : SingleClickIntent()
//    data class getFilter(val token: String) : SingleClickIntent()
}