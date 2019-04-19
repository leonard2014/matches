package com.leonard.matches.ui.viewitem

import com.leonard.matches.R
import com.leonard.matches.model.Team
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.matches_team.view.*

class TeamHeaderItem(private val team: Team) : Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.teamName.text = team.name
    }

    override fun getLayout() = R.layout.matches_team

    override fun getSpanSize(spanCount: Int, position: Int) = SINGLE_COLUMN_SPAN_COUNT
}