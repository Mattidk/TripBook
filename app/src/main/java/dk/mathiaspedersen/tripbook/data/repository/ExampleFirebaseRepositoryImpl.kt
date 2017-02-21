package dk.mathiaspedersen.tripbook.data.repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dk.mathiaspedersen.tripbook.domain.entity.Trip
import dk.mathiaspedersen.tripbook.domain.interactor.event.bus.Bus
import dk.mathiaspedersen.tripbook.domain.interactor.firebase.FirebaseInteractor
import dk.mathiaspedersen.tripbook.domain.repository.ExampleFirebaseRepository


class ExampleFirebaseRepositoryImpl(val database: FirebaseDatabase, val bus: Bus) : ExampleFirebaseRepository {

    override fun getExample(callback: FirebaseInteractor) {
        database.reference.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                callback.successful(dataSnapshot.children.map { it.getValue(Trip::class.java) })
            }

            override fun onCancelled(databaseError: DatabaseError) {
                callback.unsuccessful(databaseError.message)
            }
        })
    }
}