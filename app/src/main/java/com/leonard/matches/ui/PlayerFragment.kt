package com.leonard.matches.ui

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.kennyc.view.MultiStateView

import com.leonard.matches.R
import com.leonard.matches.model.PlayerDetail
import com.leonard.matches.repository.HEADSHOT_TEMPLATE
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.error_view.*
import kotlinx.android.synthetic.main.player_fragment.*
import java.lang.IllegalArgumentException
import javax.inject.Inject

class PlayerFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: PlayerViewModelFactory

    private lateinit var teamId: String
    private lateinit var playerId: String

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

        teamId = arguments?.getString(TEAM_ID) ?: throw IllegalArgumentException("must have teamId")
        playerId = arguments?.getString(PLAYER_ID) ?: throw IllegalArgumentException("must have playerId")
    }

    private lateinit var viewModel: PlayerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.player_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val url = String.format(HEADSHOT_TEMPLATE, playerId)
        Glide.with(context!!)
            .load(url)
            .placeholder(R.drawable.headshot_blank)
            .into(headshot)

        retry.setOnClickListener { viewModel.loadPlayerDetail(teamId, playerId) }

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PlayerViewModel::class.java)
        viewModel.viewState.observe(this, Observer { state ->
            multiStateView.viewState = when (state) {
                is PlayerViewModel.ViewState.Loading ->
                    MultiStateView.VIEW_STATE_LOADING
                is PlayerViewModel.ViewState.Error ->
                    MultiStateView.VIEW_STATE_ERROR
                is PlayerViewModel.ViewState.Content ->
                    MultiStateView.VIEW_STATE_CONTENT
            }

            if (state is PlayerViewModel.ViewState.Content) {
                showPlayerDetail(state.detail)
            }
        })

        if(savedInstanceState == null) {
            viewModel.loadPlayerDetail(teamId, playerId)
        }
    }

    private fun showPlayerDetail(playerDetail: PlayerDetail) {
        fullName.text = playerDetail.fullName
        stats.text = playerDetail.toStatsString()
    }

    private fun PlayerDetail.toStatsString() =
        "$fullName\n$dateOfBirth\n$position\n${heightCm}Cm\n${weightKg}Kg\n" +
                "\nLast match stats\n" +
                kotlin.with(lastMatchStats) {
                    "erors: ${errors}\n" +
                            "goals: $goals\n" +
                            "intercepted: $intercepted\n" +
                            "intercepts: $intercepted\n" +
                            "kicks: $kicks\n" +
                            "points: $points\n" +
                            "possessions: $possessions\n" +
                            "runs: $runs\n" +
                            "run meters: ${runMetres}m" +
                            "tackles: $tackles\n" +
                            "fantasy points: $fantasyPoints"
                }.toString()
}
