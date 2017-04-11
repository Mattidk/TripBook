package dk.mathiaspedersen.tripbook.data.entity

data class VehicleEntity(val make: String, val model: String, val year: String, val odometer: Map<String, Long>)