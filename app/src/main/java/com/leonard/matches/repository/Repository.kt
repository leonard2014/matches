package com.leonard.matches.repository

import io.reactivex.schedulers.Schedulers

class Repository(private val apiService: ApiService) {
    val matches
        get() = apiService.getMatches()
            .map { matches ->
                matches.forEach { match ->
                    match.teamA.topPlayers.forEach { player ->
                        player.teamId = match.teamA.id
                    }
                    match.teamB.topPlayers.forEach { player ->
                        player.teamId = match.teamB.id
                    }
                }
                matches
            }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())

    fun getPlayerDetail(teamId: String, playerId: String) =
        apiService.getPlayerDetail(teamId, playerId)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
}