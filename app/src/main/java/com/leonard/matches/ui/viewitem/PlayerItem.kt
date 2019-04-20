package com.leonard.matches.ui.viewitem

import com.bumptech.glide.Glide
import com.leonard.matches.R
import com.leonard.matches.model.TopPlayer
import com.leonard.matches.repository.HEADSHOT_TEMPLATE
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.matches_top_player.view.*


typealias OnPlayerClicked = (player: TopPlayer) -> Unit

class PlayerItem(private val player: TopPlayer, private val onPlayerClicked: OnPlayerClicked) : Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        with(viewHolder.itemView) {
            playerShortName.text = player.shortName
            playerStats.text = player.statValue.toString()
            playerJumper.text = player.jumperNumber.toString()
            playerPosition.text = player.position
            val URL = String.format(HEADSHOT_TEMPLATE, player.id)
            Glide.with(context)
                .load(URL)
                .placeholder(R.drawable.headshot_blank)
                .into(headshot)

            headshot.setOnClickListener{onPlayerClicked(player)}
        }
    }

    override fun getLayout() = R.layout.matches_top_player

    override fun getSpanSize(spanCount: Int, position: Int) = SINGLE_COLUMN_SPAN_COUNT
}