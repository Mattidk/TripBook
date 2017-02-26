package dk.mathiaspedersen.tripbook.domain.repository

import dk.mathiaspedersen.tripbook.domain.interactor.trip.TripInteractor

interface TripRepository {
    fun getExample(callback: TripInteractor)
}