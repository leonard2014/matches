package com.leonard.matches.repository

import io.reactivex.schedulers.Schedulers

class Repository(private val apiService: ApiService) {
    val matches
        get() = apiService.getMatches()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
}