package com.testapp.mckinleytest.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.testapp.mckinleytest.data.local.AuthPreferences
import com.testapp.mckinleytest.data.remote.ApiService
import com.testapp.mckinleytest.data.repository.AuthRepositoryImpl
import com.testapp.mckinleytest.domain.repository.AuthRepository
import com.testapp.mckinleytest.domain.use_case.LoginUseCase
import com.testapp.mckinleytest.domain.use_case.RegisterUseCase
import com.testapp.mckinleytest.util.Constants.AUTH_PREFERENCES
import com.testapp.mckinleytest.util.Constants.BASE_URL
import com.google.gson.Gson
import com.testapp.mckinleytest.domain.use_case.AddUserUsecase
import com.testapp.mckinleytest.domain.use_case.GetUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePreferenceDataStore(@ApplicationContext context: Context) : DataStore<Preferences> =
        PreferenceDataStoreFactory.create(
            produceFile = {
                context.preferencesDataStoreFile(AUTH_PREFERENCES)
            }
        )

    @Provides
    @Singleton
    fun provideAuthPreferences(dataStore: DataStore<Preferences>) =
        AuthPreferences(dataStore)


    @Provides
    @Singleton
    fun providesApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesAuthRepository(
        apiService: ApiService,
        preferences: AuthPreferences
    ): AuthRepository {
        return AuthRepositoryImpl(
            apiService = apiService,
            preferences = preferences
        )
    }

    @Provides
    @Singleton
    fun providesLoginUseCase(repository: AuthRepository): LoginUseCase {
        return LoginUseCase(repository)
    }


    @Provides
    @Singleton
    fun providesRegisterUseCase(repository: AuthRepository): RegisterUseCase {
        return RegisterUseCase(repository)
    }
    @Provides
    @Singleton
    fun providesGetUserUseCase(repository: AuthRepository): GetUserUseCase {
        return GetUserUseCase(repository)
    }
    @Provides
    @Singleton
    fun provideAddUserUsecase(repository: AuthRepository): AddUserUsecase {
        return AddUserUsecase(repository)
    }

}