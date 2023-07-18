package com.testapp.mckinleytest.data.remote.response

import com.google.gson.annotations.SerializedName

data class CreateUserResponse(
    @SerializedName("name")
    val name : String,
    @SerializedName("job")
    val job : String,
    @SerializedName("id")
    val id : String,
    @SerializedName("createdAt")
    val createdAt : String
)
