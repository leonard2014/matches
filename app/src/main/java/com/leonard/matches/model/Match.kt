package com.leonard.matches.model

import com.google.gson.annotations.SerializedName

data class Match(
    @SerializedName("match_id")
    val matchId: String,
    @SerializedName("stat_type")
    val statType: String,
    @SerializedName("team_A")
    val teamA: Team,
    @SerializedName("team_B")
    val teamB: Team
)