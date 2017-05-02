package dk.mathiaspedersen.tripbook.data.repository

import dk.mathiaspedersen.tripbook.data.entity.mapper.TripMapper
import dk.mathiaspedersen.tripbook.data.repository.datasource.TripDataSource
import dk.mathiaspedersen.tripbook.domain.entity.Trip
import dk.mathiaspedersen.tripbook.domain.repository.TripRepository
import io.reactivex.Observable

class TripDataRepository(val datasource: TripDataSource, val tripMapper: TripMapper) : TripRepository {

    override fun getTrips(): Observable<List<Trip>> {
        return datasource.getTrips().map { tripMapper.transform(it) }
    }

    override fun getTrip(key: String): Observable<Trip> {
        return datasource.getTrip(key).map { tripMapper.transform(it) }
    }

    override fun deleteTrip(key: String): Observable<String> {
        return datasource.deleteTrip(key)
    }
}