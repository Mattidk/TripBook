package dk.mathiaspedersen.tripbook.presentation.custom

import android.content.Context
import android.view.ViewGroup
import dk.mathiaspedersen.tripbook.presentation.helper.AppSettings
import dk.mathiaspedersen.tripbook.presentation.view.TripsView


class ViewHolderFactory(val context: Context, val settings: AppSettings) {

    fun createTrip(parent: ViewGroup, view: TripsView): TripViewHolder {
        return TripViewHolder(context, settings, parent, view)
    }
}