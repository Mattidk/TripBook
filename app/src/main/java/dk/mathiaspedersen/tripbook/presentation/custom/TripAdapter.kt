package dk.mathiaspedersen.tripbook.presentation.custom

import android.content.Context
import android.os.Vibrator
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import dk.mathiaspedersen.tripbook.R
import dk.mathiaspedersen.tripbook.presentation.entity.TripDetail
import dk.mathiaspedersen.tripbook.presentation.entity.TripStackDetal
import dk.mathiaspedersen.tripbook.presentation.helper.AppSettings
import dk.mathiaspedersen.tripbook.presentation.view.TripsView
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator
import jp.wasabeef.recyclerview.animators.SlideInRightAnimator
import java.util.*

class TripAdapter(val view: TripsView, var trips: ArrayList<TripDetail>, val context: Context, val settings: AppSettings,
                  val viewHolderFactory: ViewHolderFactory) : RecyclerView.Adapter<TripViewHolder>(), SwipeHelperAdapter {

    companion object {
        const val LEFT = 16
        const val RIGHT = 32
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripViewHolder {
        val viewGroup = LayoutInflater.from(parent.context).inflate(R.layout.item_trip, parent, false) as ViewGroup
        return viewHolderFactory.createTrip(viewGroup, view)
    }

    override fun onBindViewHolder(holder: TripViewHolder, position: Int) {
        holder.bind(trips[position])
    }

    override fun getItemCount(): Int = trips.size

    fun refresh(list: ArrayList<TripDetail>) {
        val currentSize = trips.size
        trips.clear()
        trips.addAll(list)
        notifyItemRangeRemoved(0, currentSize)
        notifyItemRangeInserted(0, itemCount)
    }

    fun addTrip(trip: TripStackDetal, list: RecyclerView) {
        list.scrollToPosition(trip.position)
        when (trip.direction) {
            LEFT -> {
                list.itemAnimator = SlideInLeftAnimator()
                trips.add(trip.position, trip.trip)
                notifyItemInserted(trip.position)
            }
            RIGHT -> {
                list.itemAnimator = SlideInRightAnimator()
                trips.add(trip.position, trip.trip)
                notifyItemInserted(trip.position)
            }
        }
    }

    override fun onItemDismiss(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val adapterPosition = viewHolder.adapterPosition
        val trip = trips[viewHolder.adapterPosition]

        trips.removeAt(adapterPosition)
        notifyItemRemoved(adapterPosition)
        view.pushTrip(TripStackDetal(adapterPosition, direction, trip))

        if (settings.vibrateOnClassification()) {
            val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibrator.vibrate(50)
        }
    }
}


