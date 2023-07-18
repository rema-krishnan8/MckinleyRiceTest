package com.testapp.mckinleytest.domain.use_case

import com.testapp.mckinleytest.data.models.Data
import com.testapp.mckinleytest.data.models.toUser
import com.testapp.mckinleytest.domain.model.User
import com.testapp.mckinleytest.domain.repository.AuthRepository
import com.testapp.mckinleytest.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetUserUseCase(private val repository: AuthRepository) {
    operator fun invoke(): Flow<Resource<User>> = flow {
        try {
            emit(Resource.Loading<User>())
            val users = repository.getUserDetails().toUser()
            emit(Resource.Success<User>(users))
        } catch (e: HttpException) {
            emit(Resource.Error<User>(e.localizedMessage ?: "Exception occured"))
        } catch (e: IOException) {
            emit(Resource.Error<User>(e.localizedMessage ?: "No network "))
        }
    }
}
