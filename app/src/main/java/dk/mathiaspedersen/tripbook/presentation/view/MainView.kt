package dk.mathiaspedersen.tripbook.presentation.view

import dk.mathiaspedersen.tripbook.domain.entity.Trip

interface MainView : PresentationView {
    fun example(response: List<Trip>)
}