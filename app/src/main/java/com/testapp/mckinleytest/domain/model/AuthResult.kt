package com.testapp.mckinleytest.domain.model

import com.testapp.mckinleytest.util.Resource

data class AuthResult(
    val passwordError: String? = null,
    val emailError : String? = null,
    val result: Resource<Unit>? = null
)