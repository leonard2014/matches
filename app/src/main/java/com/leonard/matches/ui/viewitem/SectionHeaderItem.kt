package com.leonard.matches.ui.viewitem

import com.leonard.matches.R
import com.leonard.matches.model.Match
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.matches_section.view.*


class SectionHeaderItem(private val match: Match) : Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.statsType.text = match.statType
    }

    override fun getLayout() = R.layout.matches_section
}