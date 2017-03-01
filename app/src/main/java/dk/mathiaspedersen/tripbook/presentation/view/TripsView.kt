package dk.mathiaspedersen.tripbook.presentation.view

import dk.mathiaspedersen.tripbook.presentation.model.EncodedTrip

interface TripsView : BaseView {
    fun example(response: List<EncodedTrip>)
    fun onError(message: String)
}