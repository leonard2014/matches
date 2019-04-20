package com.leonard.matches.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.leonard.matches.R

class PlayerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
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
