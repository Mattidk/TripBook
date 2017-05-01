package dk.mathiaspedersen.tripbook.domain.repository

import dk.mathiaspedersen.tripbook.domain.entity.Trip
import io.reactivex.Observable

interface TripRepository {
    fun getTrips(): Observable<List<Trip>>
    fun getTrip(key: String): Observable<Trip>
}
