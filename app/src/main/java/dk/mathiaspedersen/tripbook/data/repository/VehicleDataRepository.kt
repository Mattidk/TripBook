package dk.mathiaspedersen.tripbook.data.repository

import dk.mathiaspedersen.tripbook.data.entity.mapper.VehicleMapper
import dk.mathiaspedersen.tripbook.data.repository.datasource.VehicleDataSource
import dk.mathiaspedersen.tripbook.domain.entity.Vehicle
import dk.mathiaspedersen.tripbook.domain.repository.VehicleRepository
import io.reactivex.Observable

class VehicleDataRepository(val datasource: VehicleDataSource, val mapper: VehicleMapper): VehicleRepository {

    override fun getVehicle(key: String): Observable<Vehicle> {
        return datasource.getVehicle(key).map { mapper.transform(it) }
    }
}