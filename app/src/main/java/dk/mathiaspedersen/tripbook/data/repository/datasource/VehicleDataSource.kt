package dk.mathiaspedersen.tripbook.data.repository.datasource

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import dk.mathiaspedersen.tripbook.data.entity.VehicleEntity
import dk.mathiaspedersen.tripbook.data.entity.model.FirebaseVehicle
import dk.mathiaspedersen.tripbook.data.exceptions.FirebaseRxDataCastException
import dk.mathiaspedersen.tripbook.presentation.util.Utils
import io.reactivex.Observable

class VehicleDataSource(val context: Context, val database: FirebaseDatabase, val auth: FirebaseAuth, val utils: Utils) {

    /**
     * Returns the current user id
     */
    fun getUserID(): String {
        return auth.currentUser?.uid ?: throw IllegalStateException("User id was null")
    }

    fun getVehicleReference(key: String): DatabaseReference {
        return database.getReference("users").child(getUserID()).child("vehicles").child(key)
    }

    /**
     * Allows to get the list of [VehicleEntity]
     * @return
     */

    fun getVehicle(key: String): Observable<VehicleEntity> {
        return Observable.create { emitter ->
            getVehicleReference(key).addListenerForSingleValueEvent(object: ValueEventListener {

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val vehicleId = dataSnapshot.key
                    val vehicle = dataSnapshot.getValue(FirebaseVehicle::class.java)
                    val defaultIcon = utils.createIcon(vehicle)

                    val output = VehicleEntity(vehicleId, defaultIcon, vehicle.make, vehicle.model, vehicle.year, vehicle.odometer)

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
    }
}