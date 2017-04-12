package dk.mathiaspedersen.tripbook.presentation.custom

import android.content.Context
import android.view.ViewGroup
import dk.mathiaspedersen.tripbook.presentation.helper.AppSettings


class ViewHolderFactory(val context: Context, val settings: AppSettings) {

    fun createTrip(parent: ViewGroup): TripViewHolder {
        return TripViewHolder(context, settings, parent)
    }

    fun createRecent(parent: ViewGroup): RecentViewHolder {
        return RecentViewHolder(context, settings, parent)
    }
}