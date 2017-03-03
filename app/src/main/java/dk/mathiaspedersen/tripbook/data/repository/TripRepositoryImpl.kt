package dk.mathiaspedersen.tripbook.data.repository

import android.content.Context
import android.graphics.Color
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dk.mathiaspedersen.tripbook.R
import dk.mathiaspedersen.tripbook.data.entity.TripModel
import dk.mathiaspedersen.tripbook.data.entity.mapper.TripMapper
import dk.mathiaspedersen.tripbook.data.entity.model.FirebaseTrip
import dk.mathiaspedersen.tripbook.domain.interactor.event.bus.Bus
import dk.mathiaspedersen.tripbook.domain.interactor.trip.TripInteractor
import dk.mathiaspedersen.tripbook.domain.repository.TripRepository
import dk.mathiaspedersen.tripbook.presentation.util.maps.map.Map


class TripRepositoryImpl(val context: Context, val database: FirebaseDatabase, val auth: FirebaseAuth,
                         val tripMapper: TripMapper, val bus: Bus) : TripRepository {

    override fun getTrips(callback: TripInteractor) {

        val user = auth.currentUser?.uid
        if (user == null) {
            callback.unsuccessful("User id is null")
            return
        }
        database.getReference("users").child(user).child("unclassified")
                .addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                callback.successful(tripMapper.transformTrips(dataSnapshot.children.map {
                    val model = it.getValue(FirebaseTrip::class.java)
                    TripModel(Map().path(Map.Path.Style.builder().color(Color.BLUE).build(), model.route)
                            .style(context.getString(R.string.midnight_commander)))
                }))
            }

            override fun onCancelled(databaseError: DatabaseError) {
                callback.unsuccessful(databaseError.message)
            }
        })
    }
}