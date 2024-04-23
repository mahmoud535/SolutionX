package com.example.solutionx.features.savelist.presentation.ui.activity

import com.example.solutionx.features.login.domain.model.User
import com.example.solutionx.features.login.presentation.ui.activity.login.LoginState

sealed class MainListState {
    object IdleState : MainListState()
    object Loading : MainListState()
    data class Success(val data: List<String>) : MainListState()
    data class Error(val error: Throwable) : MainListState()
}