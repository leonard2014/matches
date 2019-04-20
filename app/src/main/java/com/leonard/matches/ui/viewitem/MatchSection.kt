package com.leonard.matches.ui.viewitem

import com.leonard.matches.model.Match
import com.leonard.matches.model.Team
import com.xwray.groupie.Section
import com.xwray.groupie.kotlinandroidextensions.Item

private fun createTeamColumn(team: Team, onPlayerClicked: OnPlayerClicked): ArrayList<Item> =
    arrayListOf<Item>().apply {
        add(TeamHeaderItem(team))
        addAll(team.topPlayers.map { PlayerItem(it, onPlayerClicked) })
    }

class MatchSection(match: Match, onPlayerClicked: OnPlayerClicked) : Section() {
    init {
        setHeader(SectionHeaderItem(match))
        add(ColumnGroup(createTeamColumn(match.teamA, onPlayerClicked) + createTeamColumn(match.teamB, onPlayerClicked)))
    }
}