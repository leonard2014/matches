package com.leonard.matches.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.leonard.matches.model.PlayerDetail
import com.leonard.matches.repository.Repository
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import java.io.IOException

class PlayerViewModelTest {
    @get:Rule
    val mockitoRule = MockitoJUnit.rule()

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    private lateinit var viewModel: PlayerViewModel

    @Mock
    private lateinit var playerDetail: PlayerDetail

    @Before
    fun setUp() {
        viewModel = PlayerViewModel(repository)
    }

    @Test
    fun `when loading succeeds should send loading then content state`() {
        val teamId = "1"
        val playerId = "2"

        whenever(repository.getPlayerDetail(teamId, playerId)).thenReturn(Single.just(playerDetail))
        val testObserver = viewModel.viewState.testObserver()

        viewModel.loadPlayerDetail(teamId, playerId)

        Assertions.assertThat(testObserver.observedValues.size).isEqualTo(3)
        Assertions.assertThat(testObserver.observedValues[0]).isEqualTo(PlayerViewModel.ViewState.Loading)
        Assertions.assertThat(testObserver.observedValues[1]).isEqualTo(PlayerViewModel.ViewState.Loading)
        Assertions.assertThat(testObserver.observedValues[2]).isEqualTo(PlayerViewModel.ViewState.Content(playerDetail))
    }

    @Test
    fun `when loading fails should send error state`() {
        val teamId = "1"
        val playerId = "2"

        val error = IOException()

        whenever(repository.getPlayerDetail(teamId, playerId)).thenReturn(Single.error(error))
        val testObserver = viewModel.viewState.testObserver()

        viewModel.loadPlayerDetail(teamId, playerId)

        Assertions.assertThat(testObserver.observedValues.size).isEqualTo(3)
        Assertions.assertThat(testObserver.observedValues[0]).isEqualTo(PlayerViewModel.ViewState.Loading)
        Assertions.assertThat(testObserver.observedValues[1]).isEqualTo(PlayerViewModel.ViewState.Loading)
        Assertions.assertThat((testObserver.observedValues[2] as PlayerViewModel.ViewState.Error).exception).isEqualTo(error)
    }
}