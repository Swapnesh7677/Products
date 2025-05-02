package com.swapnesh.cgpoc.data.model

import com.google.gson.annotations.SerializedName
import android.os.Parcel
import android.os.Parcelable


data class Reviews(
  @SerializedName("rating") var rating: Int? = null,
  @SerializedName("comment") var comment: String? = null,
  @SerializedName("date") var date: String? = null,
  @SerializedName("reviewerName") var reviewerName: String? = null,
  @SerializedName("reviewerEmail") var reviewerEmail: String? = null
) : Parcelable {
  constructor(parcel: Parcel) : this(
    rating = parcel.readValue(Int::class.java.classLoader) as? Int,
    comment = parcel.readString(),
    date = parcel.readString(),
    reviewerName = parcel.readString(),
    reviewerEmail = parcel.readString()
  )

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeValue(rating)
    parcel.writeString(comment)
    parcel.writeString(date)
    parcel.writeString(reviewerName)
    parcel.writeString(reviewerEmail)
  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Parcelable.Creator<Reviews> {
    override fun createFromParcel(parcel: Parcel): Reviews {
      return Reviews(parcel)
    }

    override fun newArray(size: Int): Array<Reviews?> {
      return arrayOfNulls(size)
    }
  }
}
