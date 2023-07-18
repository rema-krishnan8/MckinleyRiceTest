package com.testapp.mckinleytest.data.remote

import com.testapp.mckinleytest.data.models.Data
import com.testapp.mckinleytest.data.models.UserDto
import com.testapp.mckinleytest.data.remote.request.AuthRequest
import com.testapp.mckinleytest.data.remote.request.CreateUserRequest
import com.testapp.mckinleytest.data.remote.response.AuthResponse
import com.testapp.mckinleytest.data.remote.response.CreateUserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("api/login")
    suspend fun loginUser(
        @Body loginRequest: AuthRequest
    ) : AuthResponse


    @POST("api/register")
    suspend fun registerUser(
        @Body registerRequest: AuthRequest
    ) : AuthResponse

    @GET("api/users?page=1")
    suspend fun getUserDetails(): UserDto

    @POST("api/users")
    suspend fun createUser(@Body createUserRequest: CreateUserRequest) : CreateUserResponse

}