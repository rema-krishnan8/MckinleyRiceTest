package com.testapp.mckinleytest.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.testapp.mckinleytest.common.TextFieldState
import com.testapp.mckinleytest.common.UiEvents
import com.testapp.mckinleytest.domain.use_case.AddUserUsecase
import com.testapp.mckinleytest.util.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

class CreateUserViewModel @Inject constructor(
    private val addUserUsecase: AddUserUsecase
) : ViewModel() {
    private var _loginState = mutableStateOf(AuthState())
    val loginState: State<AuthState> = _loginState
    private val _emailState = mutableStateOf(TextFieldState())
    val emailState: State<TextFieldState> = _emailState

    fun setEmail(value: String) {
        _emailState.value = emailState.value.copy(text = value)
    }

    private val _passwordState = mutableStateOf(TextFieldState())
    val passwordState: State<TextFieldState> = _passwordState

    fun setPassword(value: String) {
        _passwordState.value = passwordState.value.copy(text = value)
    }

    fun createUser() {
        viewModelScope.launch {
            _loginState.value = loginState.value.copy(isLoading = false)

            val loginResult = addUserUsecase(
                name = emailState.value.text,
                job = passwordState.value.text
            )

            _loginState.value = loginState.value.copy(isLoading = false)

            when (loginResult.result) {
                is Resource.Success -> {
                    // getUser()
                    UiEvents.SnackbarEvent(
                        loginResult.result.message ?: "Success!"
                    )
                }
                is Resource.Error -> {
                    UiEvents.SnackbarEvent(
                        loginResult.result.message ?: "Error!"
                    )
                }
                else -> {
                }
            }
        }
    }
}
