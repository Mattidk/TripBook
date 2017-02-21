package dk.mathiaspedersen.tripbook.presentation.view

import dk.mathiaspedersen.tripbook.domain.entity.Trip

interface TripsView : PresentationView {
    fun example(response: List<Trip>)
    fun onError(message: String)
}