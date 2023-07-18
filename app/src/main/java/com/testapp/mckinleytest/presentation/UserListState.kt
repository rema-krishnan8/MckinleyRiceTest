package com.testapp.mckinleytest.presentation

import com.testapp.mckinleytest.data.models.Data
import com.testapp.mckinleytest.domain.model.User

data class UserListState(
    val isLoading: Boolean = false,
    val users: List<Data> = emptyList(),
    val error: String = ""
)
