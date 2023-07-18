package com.testapp.mckinleytest.domain.repository

import com.testapp.mckinleytest.data.models.UserDto
import com.testapp.mckinleytest.data.remote.request.AuthRequest
import com.testapp.mckinleytest.data.remote.request.CreateUserRequest
import com.testapp.mckinleytest.util.Resource

interface AuthRepository {
    suspend fun login(loginRequest: AuthRequest): Resource<Unit>
    suspend fun register(registerRequest: AuthRequest): Resource<Unit>
    suspend fun getUserDetails(): UserDto
    suspend fun createUser(userRequest: CreateUserRequest): Resource<Unit>
}
