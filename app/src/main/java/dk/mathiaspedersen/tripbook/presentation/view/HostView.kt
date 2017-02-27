package dk.mathiaspedersen.tripbook.presentation.view

import dk.mathiaspedersen.tripbook.domain.entity.Trip

interface HostView : BaseView {
    fun example(response: List<Trip>)
    fun signOutSuccessful()
    fun signOutUnsuccessful()
}