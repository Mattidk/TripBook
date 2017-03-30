package dk.mathiaspedersen.tripbook.data.repository

import android.content.Context
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dk.mathiaspedersen.tripbook.data.entity.TripEntity
import dk.mathiaspedersen.tripbook.data.entity.mapper.TripMapper
import dk.mathiaspedersen.tripbook.data.entity.model.FirebaseTrip
import dk.mathiaspedersen.tripbook.domain.entity.Trip
import dk.mathiaspedersen.tripbook.domain.interactor.GetUnclassifiedTrips
import dk.mathiaspedersen.tripbook.domain.interactor.base.Bus
import dk.mathiaspedersen.tripbook.domain.repository.FirebaseRepository
import java.util.*


class FirebaseRepositoryImpl(val context: Context, val database: FirebaseDatabase, val auth: FirebaseAuth,
                             val tripMapper: TripMapper, val bus: Bus) : FirebaseRepository {

    override fun getTrips(callback: GetUnclassifiedTrips) {

        val userID = auth.currentUser?.uid ?: throw IllegalStateException("User ID can´t be null")

        database.getReference("users").child(userID).child("unclassified")
                .addListenerForSingleValueEvent(object : ValueEventListener {

                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        callback.onSuccess(tripMapper.transform(dataSnapshot.children.map {
                            val trip = it.getValue(FirebaseTrip::class.java)
                            val start = trip.markers.start.marker
                            val end = trip.markers.end.marker
                            val coords = trip.route
                            TripEntity(it.key, Date(trip.time.toLong() * 1000), start, end, coords)
                        }))
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