package dk.mathiaspedersen.tripbook.domain.entity

data class Vehicle(val make: String, val model: String, val year: String, val odometer: Map<String, Long>)