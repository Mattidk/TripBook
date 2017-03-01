package dk.mathiaspedersen.tripbook.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dk.mathiaspedersen.tripbook.domain.interactor.event.bus.Bus
import dk.mathiaspedersen.tripbook.domain.interactor.trip.TripInteractor
import dk.mathiaspedersen.tripbook.domain.repository.TripRepository
import dk.mathiaspedersen.tripbook.presentation.model.EncodedTrip


class TripRepositoryImpl(val database: FirebaseDatabase, val auth: FirebaseAuth, val bus: Bus) : TripRepository {

    override fun getExample(callback: TripInteractor) {

        val user = auth.currentUser?.uid
        if (user == null) {
            callback.unsuccessful("User id is null")
            return
        }
        database.getReference("users").child(user).child("unclassified")
                .addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                callback.successful(dataSnapshot.children.map { it.getValue(EncodedTrip::class.java) })
            }

            override fun onCancelled(databaseError: DatabaseError) {
                callback.unsuccessful(databaseError.message)
            }
        })
    }
}