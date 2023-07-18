package com.testapp.mckinleytest.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.testapp.mckinleytest.domain.use_case.GetUserUseCase
import com.testapp.mckinleytest.domain.use_case.LoginUseCase
import com.testapp.mckinleytest.domain.use_case.RegisterUseCase
import com.testapp.mckinleytest.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class GetUserViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {
    private var _userDetailState = mutableStateOf(UserListState())
    val userDetailState: State<UserListState> = _userDetailState
    init {
        getUser()
    }
    fun getUser() {
        getUserUseCase().onEach {
            when (it) {
                is Resource.Success -> {
                    _userDetailState.value = UserListState(users = it.data?.data ?: emptyList())
                }
                is Resource.Error -> {
                    _userDetailState.value = UserListState(error = it.message ?: "Error occured")
                }
                is Resource.Loading -> {
                    _userDetailState.value = UserListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)

    }
}