package com.example.solutionx.features.singleclick.domain.repository

import android.app.Activity
import android.content.Context

interface LanguageRepository {
    fun saveLanguage(lang: String)

    fun getLanguage(): String?
}