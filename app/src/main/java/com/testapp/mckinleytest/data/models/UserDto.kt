package com.testapp.mckinleytest.data.models

import com.google.gson.annotations.SerializedName
import com.testapp.mckinleytest.domain.model.User

data class UserDto(

    @SerializedName("data")
    var data: List<Data>,
    @SerializedName("support")
    var support: Support? = Support(),
    var page: Int,
    var per_page: Int,
    var total: Int,
    var total_pages: Int

)

fun UserDto.toUser(): User {
    return User(
        data = data

    )
}
