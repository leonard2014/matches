package com.leonard.matches.model

import com.google.gson.annotations.SerializedName

data class PlayerDetail(
    @SerializedName("career_stats")
    val careerStats: CareerStats,
    @SerializedName("date_of_birth")
    val dateOfBirth: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("height_cm")
    val heightCm: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("last_match_id")
    val lastMatchId: String,
    @SerializedName("last_match_stats")
    val lastMatchStats: LastMatchStats,
    @SerializedName("other_names")
    val otherNames: String,
    @SerializedName("position")
    val position: String,
    @SerializedName("series_season_stats")
    val seriesSeasonStats: SeriesSeasonStats,
    @SerializedName("short_name")
    val shortName: String,
    @SerializedName("surname")
    val surname: String,
    @SerializedName("weight_kg")
    val weightKg: Int
)