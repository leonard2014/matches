package com.leonard.matches.di

import com.leonard.matches.ui.MatchesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeMatchesFragment(): MatchesFragment
}