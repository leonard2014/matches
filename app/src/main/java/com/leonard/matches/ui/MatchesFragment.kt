package com.leonard.matches.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.kennyc.view.MultiStateView

import com.leonard.matches.R
import com.leonard.matches.model.Match
import com.leonard.matches.ui.viewitem.*
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.error_view.*
import kotlinx.android.synthetic.main.matches_fragment.*
import javax.inject.Inject

class MatchesFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: MatchesViewModelFactory

    private lateinit var viewModel: MatchesViewModel

    private val rvAdapter = GroupAdapter<ViewHolder>()
        .apply {
            spanCount = WHOLE_ROW_SPAN_COUNT
        }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.matches_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(matchList) {
            layoutManager = GridLayoutManager(context, rvAdapter.spanCount)
                .apply {
                    spanSizeLookup = rvAdapter.spanSizeLookup
                }
            this.adapter = rvAdapter
        }

        retry.setOnClickListener{viewModel.loadMatches()}

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MatchesViewModel::class.java)
        viewModel.viewState.observe(this, Observer { state ->
            multiStateView.viewState = when (state) {
                is ViewState.Loading ->
                    MultiStateView.VIEW_STATE_LOADING
                is ViewState.Error ->
                    MultiStateView.VIEW_STATE_ERROR
                is ViewState.Empty ->
                    MultiStateView.VIEW_STATE_EMPTY
                is ViewState.Content ->
                    MultiStateView.VIEW_STATE_CONTENT
            }

            if (state is ViewState.Content) {
                populateAdapter(state.matches)
            }
        })
        viewModel.loadMatches()
    }

    private fun populateAdapter(matches: List<Match>) {
        rvAdapter.clear()
        rvAdapter.addAll(matches.map {MatchSection(it)})
    }
}
