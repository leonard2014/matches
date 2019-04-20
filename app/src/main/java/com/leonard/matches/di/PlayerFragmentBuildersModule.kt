package com.leonard.matches.di

import com.leonard.matches.ui.PlayerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class PlayerFragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributePlayerFragment(): PlayerFragment
}