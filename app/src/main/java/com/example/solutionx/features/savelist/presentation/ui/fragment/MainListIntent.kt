package com.example.solutionx.features.savelist.presentation.ui.fragment

sealed class MainListIntent {
    data class SaveNamesIntent (val names: List<String>) : MainListIntent()
    data class UpdateNamesListIntent (val names: List<String>) : MainListIntent()
}