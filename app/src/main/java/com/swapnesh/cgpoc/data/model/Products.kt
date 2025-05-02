package com.swapnesh.cgpoc.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class Products(
  @SerializedName("id") var id: Int? = null,
  @SerializedName("title") var title: String? = null,
  @SerializedName("description") var description: String? = null,
  @SerializedName("category") var category: String? = null,
  @SerializedName("price") var price: Double? = null,
  @SerializedName("discountPercentage") var discountPercentage: Double? = null,
  @SerializedName("rating") var rating: Double? = null,
  @SerializedName("stock") var stock: Int? = null,
  @SerializedName("tags") var tags: ArrayList<String> = arrayListOf(),
  @SerializedName("brand") var brand: String? = null,
  @SerializedName("sku") var sku: String? = null,
  @SerializedName("weight") var weight: Int? = null,
  @SerializedName("dimensions") var dimensions: Dimensions? = Dimensions(),
  @SerializedName("warrantyInformation") var warrantyInformation: String? = null,
  @SerializedName("shippingInformation") var shippingInformation: String? = null,
  @SerializedName("availabilityStatus") var availabilityStatus: String? = null,
  @SerializedName("reviews") var reviews: ArrayList<Reviews> = arrayListOf(),
  @SerializedName("returnPolicy") var returnPolicy: String? = null,
  @SerializedName("minimumOrderQuantity") var minimumOrderQuantity: Int? = null,
  @SerializedName("meta") var meta: Meta? = Meta(),
  @SerializedName("thumbnail") var thumbnail: String? = null,
  @SerializedName("images") var images: ArrayList<String> = arrayListOf()
) : Parcelable {
  constructor(parcel: Parcel) : this(
    id = parcel.readValue(Int::class.java.classLoader) as? Int,
    title = parcel.readString(),
    description = parcel.readString(),
    category = parcel.readString(),
    price = parcel.readValue(Double::class.java.classLoader) as? Double,
    discountPercentage = parcel.readValue(Double::class.java.classLoader) as? Double,
    rating = parcel.readValue(Double::class.java.classLoader) as? Double,
    stock = parcel.readValue(Int::class.java.classLoader) as? Int,
    tags = parcel.createStringArrayList() ?: arrayListOf(),
    brand = parcel.readString(),
    sku = parcel.readString(),
    weight = parcel.readValue(Int::class.java.classLoader) as? Int,
    dimensions = parcel.readParcelable(Dimensions::class.java.classLoader),
    warrantyInformation = parcel.readString(),
    shippingInformation = parcel.readString(),
    availabilityStatus = parcel.readString(),
    reviews = parcel.createTypedArrayList(Reviews.CREATOR) ?: arrayListOf(),
    returnPolicy = parcel.readString(),
    minimumOrderQuantity = parcel.readValue(Int::class.java.classLoader) as? Int,
    meta = parcel.readParcelable(Meta::class.java.classLoader),
    thumbnail = parcel.readString(),
    images = parcel.createStringArrayList() ?: arrayListOf()
  )

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeValue(id)
    parcel.writeString(title)
    parcel.writeString(description)
    parcel.writeString(category)
    parcel.writeValue(price)
    parcel.writeValue(discountPercentage)
    parcel.writeValue(rating)
    parcel.writeValue(stock)
    parcel.writeStringList(tags)
    parcel.writeString(brand)
    parcel.writeString(sku)
    parcel.writeValue(weight)
    parcel.writeParcelable(dimensions, flags)
    parcel.writeString(warrantyInformation)
    parcel.writeString(shippingInformation)
    parcel.writeString(availabilityStatus)
    parcel.writeTypedList(reviews)
    parcel.writeString(returnPolicy)
    parcel.writeValue(minimumOrderQuantity)
    parcel.writeParcelable(meta, flags)
    parcel.writeString(thumbnail)
    parcel.writeStringList(images)
  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Parcelable.Creator<Products> {
    override fun createFromParcel(parcel: Parcel): Products {
      return Products(parcel)
    }

    override fun newArray(size: Int): Array<Products?> {
      return arrayOfNulls(size)
    }
  }
}
