package dk.mathiaspedersen.tripbook.data.entity

import android.graphics.drawable.Drawable

data class VehicleEntity(val key: String, val icon: Drawable, val make: String, val model: String, val year: String, val odometer: Map<String, Long>)