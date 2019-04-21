package com.leonard.matches.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.leonard.matches.model.Match
import com.leonard.matches.model.Team
import com.leonard.matches.repository.Repository
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import java.io.IOException

class MatchesViewModelTest {
    @get:Rule
    val mockitoRule = MockitoJUnit.rule()

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    private lateinit var viewModel: MatchesViewModel

    @Before
    fun setUp() {
        viewModel = MatchesViewModel(repository)
    }

    @Test
    fun `when loading succeeds should send loading then content state`() {
        val matches = arrayListOf(
            Match(
                "1", "team1",
                Team(
                    "teamA", "2", "team AA", "NewCastle",
                    emptyList()
                ),
                Team(
                    "teamB", "999", "team B", "Brisbane",
                    emptyList()
                )
            )
        )

        whenever(repository.matches).thenReturn(Single.just(matches))
        val testObserver = viewModel.viewState.testObserver()

        viewModel.loadMatches()

        assertThat(testObserver.observedValues.size).isEqualTo(3)
        assertThat(testObserver.observedValues[0]).isEqualTo(MatchesViewModel.ViewState.Loading)
        assertThat(testObserver.observedValues[1]).isEqualTo(MatchesViewModel.ViewState.Loading)
        assertThat(testObserver.observedValues[2]).isEqualTo(MatchesViewModel.ViewState.Content(matches))
    }

    @Test
    fun `when loading fails should send error state`() {
        val error = IOException()

        whenever(repository.matches).thenReturn(Single.error(error))
        val testObserver = viewModel.viewState.testObserver()

        viewModel.loadMatches()

        assertThat(testObserver.observedValues.size).isEqualTo(3)
        assertThat(testObserver.observedValues[0]).isEqualTo(MatchesViewModel.ViewState.Loading)
        assertThat(testObserver.observedValues[1]).isEqualTo(MatchesViewModel.ViewState.Loading)
        assertThat((testObserver.observedValues[2] as MatchesViewModel.ViewState.Error).exception).isEqualTo(error)
    }

    @Test
    fun `when get empty content should send empty state`() {
        val emptyList = emptyList<Match>()

        whenever(repository.matches).thenReturn(Single.just(emptyList))
        val testObserver = viewModel.viewState.testObserver()

        viewModel.loadMatches()

        assertThat(testObserver.observedValues.size).isEqualTo(3)
        assertThat(testObserver.observedValues[0]).isEqualTo(MatchesViewModel.ViewState.Loading)
        assertThat(testObserver.observedValues[1]).isEqualTo(MatchesViewModel.ViewState.Loading)
        assertThat(testObserver.observedValues[2]).isEqualTo(MatchesViewModel.ViewState.Empty)
    }
}