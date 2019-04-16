package com.leonard.matches.repository

class Repository (private val apiService: ApiService) {
    val matches
    get() = apiService.getMatches()
}