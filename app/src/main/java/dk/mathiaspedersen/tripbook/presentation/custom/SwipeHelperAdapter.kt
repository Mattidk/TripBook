package dk.mathiaspedersen.tripbook.presentation.custom

import android.support.v7.widget.RecyclerView

interface SwipeHelperAdapter {
    fun onItemDismiss(viewHolder: RecyclerView.ViewHolder, direction: Int, recyclerView: RecyclerView)
}