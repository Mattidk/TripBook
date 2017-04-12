package dk.mathiaspedersen.tripbook.domain.repository

import dk.mathiaspedersen.tripbook.domain.entity.Trip
import dk.mathiaspedersen.tripbook.domain.interactor.GetRecentTrips
import dk.mathiaspedersen.tripbook.domain.interactor.GetUnclassifiedTrips

interface FirebaseRepository {
    fun getTrips(callback: GetUnclassifiedTrips)
    fun getRecent(callback: GetRecentTrips)
    fun classifyPersonalTrip(list: List<Trip>)
    fun classifyBusinessTrip(list: List<Trip>)
}