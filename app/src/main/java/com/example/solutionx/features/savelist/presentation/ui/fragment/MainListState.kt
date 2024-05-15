package com.example.solutionx.features.savelist.presentation.ui.fragment

sealed class MainListState {
    object IdleState : MainListState()
    object Loading : MainListState()
    data class Success(val data: List<String>) : MainListState()
    data class Error(val error: Throwable) : MainListState()
}