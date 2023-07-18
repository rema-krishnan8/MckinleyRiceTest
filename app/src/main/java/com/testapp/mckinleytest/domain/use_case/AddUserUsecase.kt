package com.testapp.mckinleytest.domain.use_case

import com.testapp.mckinleytest.data.remote.request.AuthRequest
import com.testapp.mckinleytest.data.remote.request.CreateUserRequest
import com.testapp.mckinleytest.domain.model.AuthResult
import com.testapp.mckinleytest.domain.model.CreateUserResult
import com.testapp.mckinleytest.domain.model.User
import com.testapp.mckinleytest.domain.repository.AuthRepository
import com.testapp.mckinleytest.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AddUserUsecase (private val repository: AuthRepository) {
    suspend operator fun invoke(
        name:String,
        job:String
    ): CreateUserResult {
        val userRequest = CreateUserRequest(
            name = name.trim(),
            job = job.trim()
        )

       return CreateUserResult(result = repository.createUser(userRequest))
    }

}