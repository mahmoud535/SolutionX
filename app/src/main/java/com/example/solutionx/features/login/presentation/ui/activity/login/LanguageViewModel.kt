package com.example.solutionx.features.login.presentation.ui.activity.login

import android.app.Activity
import android.app.AlertDialog
import android.app.Application
import android.content.Context
import android.content.DialogInterface
import android.content.res.Configuration
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.solutionx.features.singleclick.data.repositoryimp.LanguageRepositoryImp
import com.example.solutionx.features.singleclick.domain.repository.LanguageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class LanguageViewModel @Inject constructor(private val languageRepository: LanguageRepositoryImp,private val app:Application) : AndroidViewModel(app) {
    private val _languageChanged = MutableStateFlow(Unit)
    val languageChanged: StateFlow<Unit> = _languageChanged

    fun showChangeLanguagesDialog(context: Context) {
        val listItems = arrayOf("عربي", "English")
        val mBuilder = AlertDialog.Builder(context)
        mBuilder.setTitle("Choose Language")
        mBuilder.setSingleChoiceItems(listItems, -1) { dialog, which ->
            if (which == 0) {
                setLocate("ar")
                viewModelScope.launch {
                    _languageChanged.emit(Unit)
                }
            } else if (which == 1) {
                setLocate("en")
                viewModelScope.launch {
                    _languageChanged.emit(Unit)
                }
            }
            dialog.dismiss()
        }
        val mDialog = mBuilder.create()
        mDialog.show()
    }

     fun setLocate(Lang: String) {
        val locale = Locale(Lang)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        app.baseContext.resources.updateConfiguration(config, app.resources.displayMetrics)
        languageRepository.saveLanguage(Lang)
        _languageChanged.value = Unit
    }

     fun loadLocate() {
        val lang = languageRepository.getLanguage()
        lang?.let { setLocate(it) }
    }
}