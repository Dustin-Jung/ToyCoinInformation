package com.android.aop.part2.toycoininformation.data.remote.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Tag(
    @SerializedName("coin_counter") val coinCounter: Int,
    @SerializedName("ico_counter") val icoCounter: Int,
    val id: String,
    val name: String
): Parcelable
