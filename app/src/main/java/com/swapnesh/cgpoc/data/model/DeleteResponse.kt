package com.swapnesh.cgpoc.data.model
import com.google.gson.annotations.SerializedName

data class DeleteResponse (
    @SerializedName("id")
    var id : Int?  = null,
    @SerializedName("title")
    var title  : String?  = null,
    @SerializedName("isDeleted" )
    var isDeleted : Boolean? = null,
    @SerializedName("deletedOn" )
    var deletedOn : String?  = null

)