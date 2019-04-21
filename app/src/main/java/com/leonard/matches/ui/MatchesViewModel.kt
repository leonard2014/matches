package com.leonard.matches.ui

import androidx.lifecycle.*
import com.leonard.matches.model.Match
import com.leonard.matches.repository.Repository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import java.lang.IllegalArgumentException
import javax.inject.Inject

class MatchesViewModel(private val repository: Repository) : ViewModel() {
    sealed class ViewState {
        object Loading : ViewState()
        class Error(val exception: Throwable) : ViewState()
        object Empty : ViewState()
        data class Content(val matches: List<Match>) : ViewState()
    }

    private val _viewState = MutableLiveData<ViewState>().apply { value = ViewState.Loading }
    val viewState: LiveData<ViewState> = _viewState

    private var disposeBag = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposeBag.clear()
    }

    fun loadMatches() {
        _viewState.postValue(ViewState.Loading)
        disposeBag += repository.matches
            .subscribe(
                { matches ->
                    if (!matches.isEmpty()) {
                        _viewState.postValue(ViewState.Content(matches))
                    } else {
                        _viewState.postValue(ViewState.Empty)
                    }
                },
                { error ->
                    _viewState.postValue(ViewState.Error(error))
                }
            )
    }
}

class MatchesViewModelFactory
@Inject constructor(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        if (modelClass.isAssignableFrom(MatchesViewModel::class.java)) {
            MatchesViewModel(repository) as T
        } else {
            throw IllegalArgumentException("unknown model class $modelClass")
        }
}