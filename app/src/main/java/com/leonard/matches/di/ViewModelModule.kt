package com.leonard.matches.di

import com.leonard.matches.repository.Repository
import com.leonard.matches.ui.MatchesViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {
    @Provides
    fun provideMatchesViewModelFactory(repository: Repository) =
        MatchesViewModelFactory(repository)
}