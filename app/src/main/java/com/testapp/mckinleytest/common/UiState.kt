package com.testapp.mckinleytest.common

sealed class UiEvents {
    data class SnackbarEvent(val message : String) : UiEvents()
    data class NavigateEvent(val route: String) : UiEvents()
}