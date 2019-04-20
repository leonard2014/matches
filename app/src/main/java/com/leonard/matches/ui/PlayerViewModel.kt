package com.leonard.matches.ui

import androidx.lifecycle.ViewModel
import com.leonard.matches.model.PlayerDetail

class PlayerViewModel : ViewModel() {
    sealed class ViewState {
        object Loading : ViewState()
        class Error(val exception: Throwable) : ViewState()
        object Empty : ViewState()
        data class Content(val matches: PlayerDetail) : ViewState()
    }
}
