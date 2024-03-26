package com.example.solutionx.features.authentication.login.presentation.util

import androidx.datastore.preferences.core.stringPreferencesKey

object Constants {

    const val baseUrl = "https://dev.api.altashirat.solutionplus.net/"
    val ACCESS_TOKEN = stringPreferencesKey("key_access_token")
    val USER_KEY = stringPreferencesKey("key_user")
}