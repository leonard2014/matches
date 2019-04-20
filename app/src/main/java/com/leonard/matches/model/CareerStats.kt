package com.leonard.matches.model

import com.google.gson.annotations.SerializedName

data class CareerStats(
    @SerializedName("games")
    val games: Int,
    @SerializedName("points")
    val points: Int,
    @SerializedName("tries")
    val tries: Int,
    @SerializedName("win_percentage")
    val winPercentage: Double
)