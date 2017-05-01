package dk.mathiaspedersen.tripbook.data.entity.mapper

import dk.mathiaspedersen.tripbook.data.entity.VehicleEntity
import dk.mathiaspedersen.tripbook.domain.entity.Vehicle

class VehicleMapper {

    fun transform(vehicle: VehicleEntity): Vehicle {
        return Vehicle(vehicle.key, vehicle.icon, vehicle.make, vehicle.model, vehicle.year, vehicle.odometer)
    }
}