package dk.mathiaspedersen.tripbook.data.repository

import android.content.Context
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dk.mathiaspedersen.tripbook.data.entity.LocationEntity
import dk.mathiaspedersen.tripbook.data.entity.TripEntity
import dk.mathiaspedersen.tripbook.data.entity.VehicleEntity
import dk.mathiaspedersen.tripbook.data.entity.mapper.TripMapper
import dk.mathiaspedersen.tripbook.data.entity.model.FirebaseTrip
import dk.mathiaspedersen.tripbook.data.entity.model.FirebaseVehicle
import dk.mathiaspedersen.tripbook.domain.entity.Trip
import dk.mathiaspedersen.tripbook.domain.interactor.GetUnclassifiedTrips
import dk.mathiaspedersen.tripbook.domain.interactor.base.Bus
import dk.mathiaspedersen.tripbook.domain.repository.FirebaseRepository


class FirebaseRepositoryImpl(val context: Context, val database: FirebaseDatabase, val auth: FirebaseAuth,
                             val tripMapper: TripMapper, val bus: Bus) : FirebaseRepository {

    override fun getTrips(callback: GetUnclassifiedTrips) {
        val userID = auth.currentUser?.uid ?: throw IllegalStateException("User ID can´t be null")
        val start = System.nanoTime()
        database.getReference("users").child(userID).child("unclassified")
                .addListenerForSingleValueEvent(object : ValueEventListener {

                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        val keys = dataSnapshot.children.map { it.key }

                        database.getReference("users").child(userID).child("trips")
                                .addListenerForSingleValueEvent(object : ValueEventListener {

                                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                                        val trips = keys.map { dataSnapshot.child(it).getValue(FirebaseTrip::class.java) }

                                        database.getReference("users").child(userID).child("vehicles")
                                                .addListenerForSingleValueEvent(object : ValueEventListener {

                                                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                                                        callback.onSuccess(tripMapper.transform(trips.mapIndexed { i, model ->
                                                            val key = keys[i]
                                                            val path = model.path
                                                            val simplePayh = model.simplepath
                                                            val distance = model.distance
                                                            val departure = model.departure
                                                            val destination = model.destination
                                                            val purpose = model.purpose
                                                            val vehicle = dataSnapshot.child(model.vehicle).getValue(FirebaseVehicle::class.java)
                                                            TripEntity(key, path, simplePayh, VehicleEntity(vehicle.make, vehicle.model, vehicle.year, vehicle.odometer),
                                                                    purpose, distance, LocationEntity(departure.location, departure.latitude, departure.longitude,departure.timestamp),
                                                                    LocationEntity(destination.location, destination.latitude, destination.longitude, destination.timestamp))
                                                        }))
                                                        val end = System.nanoTime()
                                                        Log.d("TIMER", "Time in milliseconds: ${end - start}")
                                                    }

                                                    override fun onCancelled(databaseError: DatabaseError) {
                                                        callback.onFailure(databaseError.message)
                                                    }

                                                })
                                    }

                                    override fun onCancelled(databaseError: DatabaseError) {
                                        callback.onFailure(databaseError.message)
                                    }
                                })
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        callback.onFailure(databaseError.message)
                    }
                })
    }

    override fun classifyPersonalTrip(list: List<Trip>) {
        val trips = tripMapper.transform(list)
        trips.forEach { Log.d("GODA", "TRIP WITH KEY ${it.key} WAS CLASSIFIED AS PERSONAL") }
    }

    override fun classifyBusinessTrip(list: List<Trip>) {
        val trips = tripMapper.transform(list)
        trips.forEach { Log.d("GODA", "TRIP WITH KEY ${it.key} WAS CLASSIFIED AS BUSINESS") }
    }
}