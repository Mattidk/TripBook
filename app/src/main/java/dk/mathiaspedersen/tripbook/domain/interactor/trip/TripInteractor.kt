package dk.mathiaspedersen.tripbook.domain.interactor.trip

import dk.mathiaspedersen.tripbook.presentation.model.EncodedTrip

interface TripInteractor {
    fun getTrips()
    fun successful(response: List<EncodedTrip>)
    fun unsuccessful(message: String)
}