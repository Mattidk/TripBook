package dk.mathiaspedersen.tripbook.presentation.custom

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import dk.mathiaspedersen.tripbook.R
import dk.mathiaspedersen.tripbook.domain.entity.Trip
import dk.mathiaspedersen.tripbook.presentation.model.EncodedTrip
import org.jetbrains.anko.find

class TripsListAdapter(var trips: List<EncodedTrip>)
    : RecyclerView.Adapter<TripsListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_trip, parent, false)
        return TripsListAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setItem(trips[position])
    }

    override fun getItemCount(): Int = trips.size

    fun refresh(list: List<EncodedTrip>) {
        trips = list
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val info: ImageView = view.find(R.id.iv_map)

        fun setItem(item: EncodedTrip) {
//            Glide.with()
//            info.text = item.route
        }
    }
}