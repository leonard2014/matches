package com.leonard.matches.ui

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.leonard.matches.R
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class PlayerFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: PlayerViewModelFactory

    companion object {
        fun newInstance(teamId: String, playerId: String) =
            PlayerFragment().apply {
                arguments = Bundle().apply {
                    putString(TEAM_ID, teamId)
                    putString(PLAYER_ID, playerId)
                }
            }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
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
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PlayerViewModel::class.java)
    }

}
