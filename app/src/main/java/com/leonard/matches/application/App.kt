package com.leonard.matches.application

import android.app.Activity
import android.app.Application
import com.leonard.matches.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class App : Application(), HasActivityInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent
            .factory()
            .appComponent()
            .inject(this)
    }

}