package dk.mathiaspedersen.tripbook.data.repository.datasource

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import dk.mathiaspedersen.tripbook.data.entity.LocationEntity
import dk.mathiaspedersen.tripbook.data.entity.TripEntity
import dk.mathiaspedersen.tripbook.data.entity.VehicleEntity
import dk.mathiaspedersen.tripbook.data.entity.model.FirebaseTrip
import dk.mathiaspedersen.tripbook.data.entity.model.FirebaseVehicle
import dk.mathiaspedersen.tripbook.data.exceptions.FirebaseRxDataCastException
import dk.mathiaspedersen.tripbook.data.exceptions.FirebaseRxDataException
import dk.mathiaspedersen.tripbook.presentation.util.Utils
import io.reactivex.Observable
import org.ocpsoft.prettytime.PrettyTime
import java.util.*

class TripDataSource(val context: Context, val database: FirebaseDatabase, val auth: FirebaseAuth, val utils: Utils) {

    /**
     * Returns the current user id
     */
    fun getUserID(): String {
        return auth.currentUser?.uid ?: throw IllegalStateException("User id was null")
    }

    fun getTripsReference(): DatabaseReference {
        return database.getReference("users").child(getUserID()).child("unclassified")
    }

    fun getTripReference(key: String): DatabaseReference {
        return database.getReference("users").child(getUserID()).child("trips").child(key)
    }

    fun getUnclassifiedTripReference(key: String): DatabaseReference {
        return database.getReference("users").child(getUserID()).child("unclassified").child(key)
    }

    /**
     * Allows to get the list of [TripEntity]
     * @return
     */

    fun getTrip(key: String): Observable<TripEntity> {
        return Observable.create { emitter ->
            getTripReference(key).addListenerForSingleValueEvent(object: ValueEventListener {

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val tripKey = dataSnapshot.key
                    val trip = dataSnapshot.getValue(FirebaseTrip::class.java)

                    database.getReference("users").child(getUserID()).child("vehicles").child(trip.vehicle)
                            .addListenerForSingleValueEvent(object : ValueEventListener {

                                override fun onDataChange(dataSnapshot: DataSnapshot) {
                                        val finalkey = tripKey
                                        val path = trip.path
                                        val simplePath = utils.createStaticMap(trip)
                                        val distance = trip.distance
                                        val departure = trip.departure
                                        val destination = trip.destination
                                        val purpose = trip.purpose
                                        val time = PrettyTime().format(Date(trip.destination.timestamp * 1000))
                                        val vehicleId = dataSnapshot.key
                                        val vehicle = dataSnapshot.getValue(FirebaseVehicle::class.java)
                                        val defaultIcon = utils.createIcon(vehicle)

                                        val output = TripEntity(finalkey, path, simplePath, VehicleEntity(vehicleId, defaultIcon, vehicle.make, vehicle.model, vehicle.year, vehicle.odometer), time,
                                                purpose, distance, LocationEntity(departure.location, departure.latitude, departure.longitude, departure.timestamp),
                                                LocationEntity(destination.location, destination.latitude, destination.longitude, destination.timestamp))

                                    if (output != null) {
                                        if (!emitter.isDisposed) {
                                            emitter.onNext(output)
                                        }
                                    } else {
                                        if (!emitter.isDisposed) {
                                            emitter.onError(FirebaseRxDataCastException("Unable to cast Firebase data response to "))
                                        }
                                    }
                                }

                                override fun onCancelled(databaseError: DatabaseError) {
                                    emitter.onError(Throwable(databaseError.message))
                                }

                            })

                }

                override fun onCancelled(error: DatabaseError) {
                    emitter.onError(Throwable(error.message))
                }
            })
        }
    }

    fun deleteTrip(key: String): Observable<String> {
        return Observable.create { emitter ->
            getUnclassifiedTripReference(key).removeValue(object: DatabaseReference.CompletionListener {
                override fun onComplete(p0: DatabaseError?, p1: DatabaseReference?) {
                    if (p0 == null) {
                        emitter.onNext("$p1 successfully deleted")
                    }else {
                        emitter.onError(Throwable("Trip was not deleted"))
                    }
                }
            })
        }
    }


    fun getTrips(): Observable<List<TripEntity>> {
        return Observable.create { emitter ->
            emitter.onNext(arrayListOf<TripEntity>())
            getTripsReference().addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {

                    val keys = dataSnapshot.children.map { it.key }

                    database.getReference("users").child(getUserID()).child("trips")
                            .addListenerForSingleValueEvent(object : ValueEventListener {

                                override fun onDataChange(dataSnapshot: DataSnapshot) {
                                    val trips = keys.map { dataSnapshot.child(it).getValue(FirebaseTrip::class.java) }

                                    database.getReference("users").child(getUserID()).child("vehicles")
                                            .addListenerForSingleValueEvent(object : ValueEventListener {

                                                override fun onDataChange(dataSnapshot: DataSnapshot) {
                                                    val value = trips.mapIndexed { i, model ->
                                                        val key = keys[i]
                                                        val path = model.path
                                                        val simplePath = utils.createStaticMap(model)
                                                        val distance = model.distance
                                                        val departure = model.departure
                                                        val destination = model.destination
                                                        val purpose = model.purpose
                                                        val time = PrettyTime().format(Date(model.destination.timestamp * 1000))
                                                        val vehicleId = dataSnapshot.child(model.vehicle).key
                                                        val vehicle = dataSnapshot.child(model.vehicle).getValue(FirebaseVehicle::class.java)
                                                        val defaultIcon = utils.createIcon(vehicle)

                                                        TripEntity(key, path, simplePath, VehicleEntity(vehicleId, defaultIcon, vehicle.make, vehicle.model, vehicle.year, vehicle.odometer), time,
                                                                purpose, distance, LocationEntity(departure.location, departure.latitude, departure.longitude, departure.timestamp),
                                                                LocationEntity(destination.location, destination.latitude, destination.longitude, destination.timestamp)) }

                                                    if (value != null) {
                                                        if (!emitter.isDisposed) {
                                                            emitter.onNext(value)
                                                        }
                                                    } else {
                                                        if (!emitter.isDisposed) {
                                                            emitter.onError(FirebaseRxDataCastException("Unable to cast Firebase data response to "))
                                                        }
                                                    }
                                                }

                                                override fun onCancelled(databaseError: DatabaseError) {
                                                    emitter.onError(Throwable(databaseError.message))
                                                }

                                            })
                                }

                                override fun onCancelled(databaseError: DatabaseError) {
                                    emitter.onError(Throwable(databaseError.message))
                                }
                            })
                }

                override fun onCancelled(error: DatabaseError) {
                    if (!emitter.isDisposed) {
                        emitter.onError(FirebaseRxDataException(error))
                    }
                }
            })
        }
    }
}