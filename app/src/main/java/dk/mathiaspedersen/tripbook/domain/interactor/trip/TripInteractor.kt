package dk.mathiaspedersen.tripbook.domain.interactor.trip

import dk.mathiaspedersen.tripbook.domain.entity.Trip

interface TripInteractor {
    fun getTrips()
    fun successful(response: List<Trip>)
    fun unsuccessful(message: String)
}