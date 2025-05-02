package com.swapnesh.cgpoc.data.model

import com.google.gson.annotations.SerializedName
import android.os.Parcel
import android.os.Parcelable


data class Meta(
  @SerializedName("createdAt") var createdAt: String? = null,
  @SerializedName("updatedAt") var updatedAt: String? = null,
  @SerializedName("barcode") var barcode: String? = null,
  @SerializedName("qrCode") var qrCode: String? = null
) : Parcelable {
  constructor(parcel: Parcel) : this(
    createdAt = parcel.readString(),
    updatedAt = parcel.readString(),
    barcode = parcel.readString(),
    qrCode = parcel.readString()
  )

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeString(createdAt)
    parcel.writeString(updatedAt)
    parcel.writeString(barcode)
    parcel.writeString(qrCode)
  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Parcelable.Creator<Meta> {
    override fun createFromParcel(parcel: Parcel): Meta {
      return Meta(parcel)
    }

    override fun newArray(size: Int): Array<Meta?> {
      return arrayOfNulls(size)
    }
  }
}
