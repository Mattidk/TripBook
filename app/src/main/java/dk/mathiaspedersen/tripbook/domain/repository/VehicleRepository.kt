package dk.mathiaspedersen.tripbook.domain.repository

import dk.mathiaspedersen.tripbook.domain.entity.Vehicle
import io.reactivex.Observable

interface VehicleRepository {
    fun getVehicle(key: String): Observable<Vehicle>
}