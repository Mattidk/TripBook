package dk.mathiaspedersen.tripbook.presentation.entity.mapper

import dk.mathiaspedersen.tripbook.domain.entity.Vehicle
import dk.mathiaspedersen.tripbook.presentation.entity.VehicleDetail

class VehicleDetailDataMapper {

    fun transform(vehicle: Vehicle): VehicleDetail {
        return VehicleDetail(vehicle.key, vehicle.icon, vehicle.make, vehicle.model, vehicle.year, vehicle.odometer)
    }
}