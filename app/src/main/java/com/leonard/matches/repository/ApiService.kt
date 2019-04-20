package com.leonard.matches.repository

import com.leonard.matches.model.Match
import com.leonard.matches.model.PlayerDetail
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("3.0/api/sports/league/matches/NRL20172101/topplayerstats.json;type=fantasy_points;type=tackles;type=runs;type=run_metres?limit=5&userkey=A00239D3-45F6-4A0A-810C-54A347F144C2")
    fun getMatches(): Single<List<Match>>

    @GET("3.0/api/sports/league/series/1/seasons/115/teams/{teamId}/players/{playerId}/detailedstats.json?userkey=A00239D3-45F6-4A0A-810C-54A347F144C2")
    fun getPlayerDetail(@Path("teamId") teamId: String, @Path("playerId") playerId: String): Single<PlayerDetail>
}