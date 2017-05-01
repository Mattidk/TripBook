package dk.mathiaspedersen.tripbook.presentation.entity

import android.graphics.drawable.Drawable

class VehicleDetail(val key: String, val icon: Drawable, val make: String, val model: String, val year: String, val odometer: Map<String, Long>)