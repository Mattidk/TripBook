package dk.mathiaspedersen.tripbook.domain.entity

import android.graphics.drawable.Drawable

data class Vehicle(val key: String, val icon: Drawable, val make: String, val model: String, val year: String, val odometer: Map<String, Long>)