package dk.mathiaspedersen.tripbook.domain.repository

import dk.mathiaspedersen.tripbook.domain.interactor.GetUnclassifiedTrips

interface FirebaseRepository {
    fun getTrips(callback: GetUnclassifiedTrips)
    fun classifyPersonalTrip(key: String?)
    fun classifyBusinessTrip(key: String?)
}