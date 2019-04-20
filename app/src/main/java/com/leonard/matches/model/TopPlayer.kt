package com.leonard.matches.model

import com.google.gson.annotations.SerializedName

data class TopPlayer(
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("jumper_number")
    val jumperNumber: Int,
    @SerializedName("position")
    val position: String,
    @SerializedName("short_name")
    val shortName: String,
    @SerializedName("stat_value")
    val statValue: Int,
    @Transient
    var teamId: String
)