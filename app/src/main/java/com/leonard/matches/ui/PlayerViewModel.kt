package com.leonard.matches.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.leonard.matches.model.PlayerDetail
import com.leonard.matches.repository.Repository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import javax.inject.Inject

class PlayerViewModel(private val repository: Repository) : ViewModel() {
    sealed class ViewState {
        object Loading : ViewState()
        class Error(val exception: Throwable) : ViewState()
        data class Content(val detail: PlayerDetail) : ViewState()
    }

    var viewState = MutableLiveData<PlayerViewModel.ViewState>().apply { value = PlayerViewModel.ViewState.Loading }
        private set

    private var disposeBag = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposeBag.clear()
    }

    fun loadPlayerDetail(teamId: String, playerId: String) {
        viewState.postValue(PlayerViewModel.ViewState.Loading)
        disposeBag += repository.getPlayerDetail(teamId, playerId)
            .subscribe(
                { detail ->
                    viewState.postValue(PlayerViewModel.ViewState.Content(detail))
                },
                { error ->
                    viewState.postValue(PlayerViewModel.ViewState.Error(error))
                }
            )
    }
}

class PlayerViewModelFactory
@Inject constructor(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        if (modelClass.isAssignableFrom(PlayerViewModel::class.java)) {
            PlayerViewModel(repository) as T
        } else {
            throw IllegalArgumentException("unknown model class $modelClass")
        }
}