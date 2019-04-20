package com.leonard.matches.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.leonard.matches.R

class PlayerFragment : Fragment() {

    companion object {
        fun newInstance(teamId: String, playerId: String) =
            PlayerFragment().apply {
                arguments = Bundle().apply {
                    putString(TEAM_ID, teamId)
                    putString(PLAYER_ID, playerId)
                }
            }
    }

    private lateinit var viewModel: PlayerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.player_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PlayerViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
