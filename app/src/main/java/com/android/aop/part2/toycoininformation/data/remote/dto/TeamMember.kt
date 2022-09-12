package com.android.aop.part2.toycoininformation.data.remote.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TeamMember(
    val id: String,
    val name: String,
    val position: String
): Parcelable
