package dk.mathiaspedersen.tripbook.domain.interactor

import dk.mathiaspedersen.tripbook.domain.entity.Trip
import dk.mathiaspedersen.tripbook.domain.interactor.event.bus.Bus
import dk.mathiaspedersen.tripbook.domain.interactor.event.trip.FetchTripsErrorEvent
import dk.mathiaspedersen.tripbook.domain.interactor.event.trip.FetchTripsEvent
import dk.mathiaspedersen.tripbook.domain.interactor.trip.TripInteractor
import dk.mathiaspedersen.tripbook.domain.repository.TripRepository

class ExampleInteractorImpl(val repository: TripRepository, val bus: Bus) : TripInteractor {

    override fun getTrips() {
        repository.getTrips(this)
    }

    override fun successful(response: List<Trip>) {
        bus.post(FetchTripsEvent(response))
    }

    override fun unsuccessful(message: String) {
        bus.post(FetchTripsErrorEvent(message))
    }

}