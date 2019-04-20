package com.leonard.matches.ui

import androidx.lifecycle.*
import com.leonard.matches.model.Match
import com.leonard.matches.repository.Repository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import java.lang.IllegalArgumentException

sealed class ViewState {
    object Loading : ViewState()
    class Error(val exception: Throwable) : ViewState()
    object Empty : ViewState()
    data class Content(val matches: List<Match>) : ViewState()
}


class MatchesViewModel(private val repository: Repository) : ViewModel() {
    var viewState = MutableLiveData<ViewState>().apply { value = ViewState.Loading }
        private set

    private var disposeBag = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposeBag.clear()
    }

    fun loadMatches() {
        viewState.postValue(ViewState.Loading)
        disposeBag += repository.matches
            .subscribe(
                { matches ->
                    if (!matches.isEmpty()) {
                        viewState.postValue(ViewState.Content(matches))
                    } else {
                        viewState.postValue(ViewState.Empty)
                    }
                },
                { error ->
                    viewState.postValue(ViewState.Error(error))
                }
            )
    }
}

class MatchesViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        if (modelClass.isAssignableFrom(MatchesViewModel::class.java)) {
            MatchesViewModel(repository) as T
        } else {
            throw IllegalArgumentException("unknown model class $modelClass")
        }
}