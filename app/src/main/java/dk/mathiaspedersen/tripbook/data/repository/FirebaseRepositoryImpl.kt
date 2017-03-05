package dk.mathiaspedersen.tripbook.data.repository

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dk.mathiaspedersen.tripbook.data.entity.TripEntity
import dk.mathiaspedersen.tripbook.data.entity.mapper.TripMapper
import dk.mathiaspedersen.tripbook.data.entity.model.FirebaseTrip
import dk.mathiaspedersen.tripbook.domain.interactor.GetUnclassifiedTrips
import dk.mathiaspedersen.tripbook.domain.interactor.base.Bus
import dk.mathiaspedersen.tripbook.domain.repository.FirebaseRepository


class FirebaseRepositoryImpl(val context: Context, val database: FirebaseDatabase, val auth: FirebaseAuth,
                             val tripMapper: TripMapper, val bus: Bus) : FirebaseRepository {

    override fun getTrips(callback: GetUnclassifiedTrips) {

        val user = auth.currentUser?.uid
        if (user == null) {
            callback.onFailure("User id is null")
            return
        }
        database.getReference("users").child(user).child("unclassified")
                .addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                callback.onSuccess(tripMapper.transformTrips(dataSnapshot.children.map {
                    TripEntity(it.getValue(FirebaseTrip::class.java).route)
                }))
            }

            override fun onCancelled(databaseError: DatabaseError) {
                callback.onFailure(databaseError.message)
            }
        })
    }
}