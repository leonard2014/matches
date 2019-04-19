package com.leonard.matches.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders

import com.leonard.matches.R
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class MatchesFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: MatchesViewModelFactory

    private lateinit var viewModel: MatchesViewModel

    companion object {
        fun newInstance() = MatchesFragment()
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MatchesViewModel::class.java)
    }

}
