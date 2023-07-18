package com.testapp.mckinleytest.data.remote.request

import com.google.gson.annotations.SerializedName

data class CreateUserRequest(
    @SerializedName("name")
    var name: String,
    @SerializedName("job")
    var job: String
)
