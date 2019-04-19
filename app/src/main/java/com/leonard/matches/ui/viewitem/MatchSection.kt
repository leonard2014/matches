package com.leonard.matches.ui.viewitem

import com.leonard.matches.model.Match
import com.leonard.matches.model.Team
import com.xwray.groupie.Section
import com.xwray.groupie.kotlinandroidextensions.Item

private fun createTeamColumn(team: Team): ArrayList<Item> =
    arrayListOf<Item>().apply {
        add(TeamHeaderItem(team))
        addAll(team.topPlayers.map { PlayerItem(it) })
    }

class MatchSection(match: Match) : Section() {
    init {
        setHeader(SectionHeaderItem(match))
        add(ColumnGroup(createTeamColumn(match.teamA) + createTeamColumn(match.teamB)))
    }
}