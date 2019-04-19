package com.leonard.matches.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.kennyc.view.MultiStateView

import com.leonard.matches.R
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.matches_fragment.*
import javax.inject.Inject

class MatchesFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: MatchesViewModelFactory

    private lateinit var viewModel: MatchesViewModel

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
        })
    }
}
