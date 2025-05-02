package com.swapnesh.cgpoc.data.model

import com.google.gson.annotations.SerializedName

data class AllUsersResponse(
    @SerializedName("users")
    var users: List<User> = arrayListOf(),
    @SerializedName("total")
    var total: Int? = null,
    @SerializedName("skip")
    var skip: Int? = null,
    @SerializedName("limit")
    var limit: Int? = null
)