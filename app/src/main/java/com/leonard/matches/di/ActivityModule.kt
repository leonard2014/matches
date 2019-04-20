package com.leonard.matches.di

import com.leonard.matches.ui.MainActivity
import com.leonard.matches.ui.PlayerActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [MainFragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [PlayerFragmentBuildersModule::class])
    abstract fun contributePlayerActivity(): PlayerActivity
}