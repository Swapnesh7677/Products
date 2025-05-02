package com.swapnesh.cgpoc.data.model


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class Dimensions (

  @SerializedName("width"  ) var width  : Double? = null,
  @SerializedName("height" ) var height : Double? = null,
  @SerializedName("depth"  ) var depth  : Double? = null

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double
    ) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel, p1: Int) {
        p0.writeValue(depth)
        p0.writeValue(width)
        p0.writeValue(height)
    }

    companion object CREATOR : Parcelable.Creator<Dimensions> {
        override fun createFromParcel(parcel: Parcel): Dimensions {
            return Dimensions(parcel)
        }

        override fun newArray(size: Int): Array<Dimensions?> {
            return arrayOfNulls(size)
        }
    }
}