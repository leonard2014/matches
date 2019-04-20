package com.leonard.matches.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.leonard.matches.R
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class PlayerActivity : AppCompatActivity(), HasSupportFragmentInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector() = dispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        val teamId = intent?.extras?.getString(TEAM_ID)
        val playerId = intent?.extras?.getString(PLAYER_ID)
        if (teamId == null || playerId == null) {
            throw IllegalArgumentException("PlayerActivity intent must have teamId and playerId")
        }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PlayerFragment.newInstance(teamId, playerId))
                .commit()
        }
    }

    companion object {
        fun startPlayerActivity(context: Context, teamId: String, playerId: String) =
            context.startActivity(
                Intent(context, PlayerActivity::class.java)
                    .apply {
                        putExtra(TEAM_ID, teamId)
                        putExtra(PLAYER_ID, playerId)
                    }
            )
    }
}
