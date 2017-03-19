package dk.mathiaspedersen.tripbook.presentation.custom

import android.content.Context
import android.os.Vibrator
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import dk.mathiaspedersen.tripbook.R
import dk.mathiaspedersen.tripbook.domain.interactor.ClassifyBusinessTrip
import dk.mathiaspedersen.tripbook.domain.interactor.ClassifyPersonalTrip
import dk.mathiaspedersen.tripbook.domain.interactor.base.firebase.FirebaseInteractorExecutor
import dk.mathiaspedersen.tripbook.presentation.entity.TripDetail
import dk.mathiaspedersen.tripbook.presentation.helper.AppSettings
import java.util.*


class TripAdapter(var trips: ArrayList<TripDetail>,
                  val context: Context, val settings: AppSettings,
                  val viewHolderFactory: ViewHolderFactory,
                  val classifyPersonalInteractor: ClassifyPersonalTrip,
                  val classifyBusinessTrip: ClassifyBusinessTrip,
                  val interactorExecutor: FirebaseInteractorExecutor)
    : RecyclerView.Adapter<ViewHolder>(), SwipeHelperAdapter {

    companion object {
        const val LEFT = 16
        const val RIGHT = 32
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_trip, parent, false) as ViewGroup
        return viewHolderFactory.create(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(trips[position])
    }

    override fun getItemCount(): Int = trips.size

    fun refresh(list: ArrayList<TripDetail>) {
        trips = list
        notifyDataSetChanged()
    }

    override fun onItemDismiss(position: Int, direction: Int) {

        when (direction) {
            LEFT -> {
                val classifyPersonalInteractorDetail = classifyPersonalInteractor
                classifyPersonalInteractorDetail.key = trips[position].key
                interactorExecutor.execute(classifyPersonalInteractorDetail)
            }
            RIGHT -> {
                val classifyBusinessInteractorDetail = classifyBusinessTrip
                classifyBusinessInteractorDetail.key = trips[position].key
                interactorExecutor.execute(classifyBusinessInteractorDetail)
            }
        }

        if (settings.vibrateOnClassification()) {
            val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibrator.vibrate(50)
        }

        trips.removeAt(position)
        notifyItemRemoved(position)
    }
}


