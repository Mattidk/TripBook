package dk.mathiaspedersen.tripbook.presentation.custom

import android.content.Context
import android.os.Vibrator
import android.support.design.widget.Snackbar
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import dk.mathiaspedersen.tripbook.R
import dk.mathiaspedersen.tripbook.presentation.entity.TripDetail
import dk.mathiaspedersen.tripbook.presentation.helper.AppSettings
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator
import jp.wasabeef.recyclerview.animators.SlideInRightAnimator
import java.util.*

class TripAdapter(var trips: ArrayList<TripDetail>,
                  val context: Context, val settings: AppSettings,
                  val viewHolderFactory: ViewHolderFactory)
    : RecyclerView.Adapter<TripViewHolder>(), SwipeHelperAdapter {

    val personalTripsToRemove = arrayListOf<TripDetail>()
    val businessTripsToRemove = arrayListOf<TripDetail>()

    companion object {
        const val LEFT = 16
        const val RIGHT = 32
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_trip, parent, false) as ViewGroup
        return viewHolderFactory.createTrip(view)
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
        notifyItemRangeInserted(0, trips.size)
    }

    override fun onItemDismiss(viewHolder: RecyclerView.ViewHolder, direction: Int, recyclerView: RecyclerView) {
        val adapterPosition = viewHolder.adapterPosition
        val trip = trips[viewHolder.adapterPosition]

        when (direction) {
            LEFT -> {
                Snackbar.make(recyclerView, context.getString(R.string.adapter_trip_snackbar_undo_personal), Snackbar.LENGTH_SHORT)
                        .setAction(R.string.adapter_trip_snackbar_undo, {
                            recyclerView.itemAnimator = SlideInLeftAnimator()
                            if (adapterPosition == 0) {recyclerView.scrollToPosition(adapterPosition)}
                            trips.add(adapterPosition, trip)
                            notifyItemInserted(adapterPosition)
                            personalTripsToRemove.remove(trip)
                        }).show()
                trips.removeAt(adapterPosition)
                notifyItemRemoved(adapterPosition)
                personalTripsToRemove.add(trip)
            }
            RIGHT -> {
                Snackbar.make(recyclerView, context.getString(R.string.adapter_trip_snackbar_undo_business), Snackbar.LENGTH_SHORT)
                        .setAction(R.string.adapter_trip_snackbar_undo, {
                            recyclerView.itemAnimator = SlideInRightAnimator()
                            if (adapterPosition == 0) recyclerView.scrollToPosition(adapterPosition)
                            trips.add(adapterPosition, trip)
                            notifyItemInserted(adapterPosition)
                            businessTripsToRemove.remove(trip)
                        }).show()
                trips.removeAt(adapterPosition)
                notifyItemRemoved(adapterPosition)
                businessTripsToRemove.add(trip)
            }
        }

        if (settings.vibrateOnClassification()) {
            val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibrator.vibrate(50)
        }
    }

    fun saveChanges() {
        personalTripsToRemove.clear()
        businessTripsToRemove.clear()
    }
}


