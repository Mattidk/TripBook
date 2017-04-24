package dk.mathiaspedersen.tripbook.presentation.custom

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import dk.mathiaspedersen.tripbook.R
import dk.mathiaspedersen.tripbook.presentation.entity.TripDetail
import dk.mathiaspedersen.tripbook.presentation.helper.AppSettings
import java.util.*

class RecentAdapter(var trips: ArrayList<TripDetail>,
                  val context: Context, val settings: AppSettings,
                  val viewHolderFactory: ViewHolderFactory)
    : RecyclerView.Adapter<RecentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_recent, parent, false) as ViewGroup
        return viewHolderFactory.createRecent(view)
    }

    override fun onBindViewHolder(holder: RecentViewHolder, position: Int) {
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

}


