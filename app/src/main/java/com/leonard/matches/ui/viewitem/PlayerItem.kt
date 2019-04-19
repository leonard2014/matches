package com.leonard.matches.ui.viewitem

import com.leonard.matches.R
import com.leonard.matches.model.TopPlayer
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.matches_top_player.view.*


class PlayerItem(private val player: TopPlayer) : Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        with(viewHolder.itemView) {
            playerShortName.text = player.shortName
            playerStats.text = player.statValue.toString()
            playerJumper.text = player.jumperNumber.toString()
            playerPosition.text = player.position
        }
    }

    override fun getLayout() = R.layout.matches_top_player

    override fun getSpanSize(spanCount: Int, position: Int) = SINGLE_COLUMN_SPAN_COUNT
}