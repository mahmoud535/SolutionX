package com.example.solutionx.features.singleclick.data.repositoryimp

import android.app.Activity
import android.content.Context
import com.example.solutionx.features.singleclick.domain.repository.LanguageRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LanguageRepositoryImp @Inject constructor(@ApplicationContext private val context: Context) : LanguageRepository {

    override fun saveLanguage(lang: String) {
        val editor = context.getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
        editor.putString("My_Lang", lang)
        editor.apply()
    }

    override fun getLanguage(): String? {
        val sharedPreferences = context.getSharedPreferences("Settings", Activity.MODE_PRIVATE)
        return sharedPreferences.getString("My_Lang", null)
    }
}