package com.leonard.matches.repository

import com.leonard.matches.model.Matches
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {
    @GET("3.0/api/sports/league/matches/NRL20172101/topplayerstats.json;type=fantasy_points;type=tackles;type=runs;type=run_metres?limit=5&userkey=A00239D3-45F6-4A0A-810C-54A347F144C2")
    fun getMatches(): Single<Matches>
}