package com.leonard.matches.model

import com.google.gson.annotations.SerializedName

data class Team(
    @SerializedName("code")
    val code: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("short_name")
    val shortName: String,
    @SerializedName("top_players")
    val topPlayers: List<TopPlayer>
)