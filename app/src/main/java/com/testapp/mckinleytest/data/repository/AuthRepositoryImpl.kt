package com.testapp.mckinleytest.data.repository

import com.testapp.mckinleytest.data.local.AuthPreferences
import com.testapp.mckinleytest.data.models.Data
import com.testapp.mckinleytest.data.models.UserDto
import com.testapp.mckinleytest.data.remote.ApiService
import com.testapp.mckinleytest.data.remote.request.AuthRequest
import com.testapp.mckinleytest.data.remote.request.CreateUserRequest
import com.testapp.mckinleytest.domain.repository.AuthRepository
import com.testapp.mckinleytest.util.Resource
import retrofit2.HttpException
import java.io.IOException

class AuthRepositoryImpl(
    private val apiService: ApiService,
    private val preferences: AuthPreferences

) : AuthRepository {
    override suspend fun login(loginRequest: AuthRequest): Resource<Unit> {
        return try {
            val response = apiService.loginUser(loginRequest)
            preferences.saveAuthToken(response.token)
            Resource.Success(Unit)
        } catch (e: IOException) {
            Resource.Error("${e.message}")
        } catch (e: HttpException) {
            Resource.Error("${e.message}")
        }
    }

    override suspend fun register(registerRequest: AuthRequest): Resource<Unit> {
        return try {
            val response = apiService.registerUser(registerRequest)
            preferences.saveAuthToken(response.token)
            Resource.Success(Unit)
        } catch (e: IOException) {
            Resource.Error("${e.message}")
        } catch (e: HttpException) {
            Resource.Error("${e.message}")
        }
    }
    override suspend fun getUserDetails(): UserDto {
        return apiService.getUserDetails()
    }

    override suspend fun createUser(userRequest: CreateUserRequest): Resource<Unit> {
         return try {
            val response = apiService.createUser(userRequest)
            Resource.Success(Unit)
        } catch (e: IOException) {
            Resource.Error("${e.message}")
        } catch (e: HttpException) {
            Resource.Error("${e.message}")
        }
    }
}
