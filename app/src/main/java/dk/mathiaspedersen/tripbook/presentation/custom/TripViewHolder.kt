package dk.mathiaspedersen.tripbook.presentation.custom

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import dk.mathiaspedersen.tripbook.R
import dk.mathiaspedersen.tripbook.presentation.entity.TripDetail
import dk.mathiaspedersen.tripbook.presentation.helper.AppSettings
import dk.mathiaspedersen.tripbook.presentation.util.staticmaps.map.StaticMap
import dk.mathiaspedersen.tripbook.presentation.view.TripsView
import org.jetbrains.anko.find
import java.text.DecimalFormat

class TripViewHolder(val context: Context, val settings: AppSettings, val parent: View, val tripsView: TripsView)
    : RecyclerView.ViewHolder(parent), RequestListener<StaticMap, GlideDrawable>, View.OnClickListener, Toolbar.OnMenuItemClickListener {

    val progress: ProgressBar = parent.find(R.id.progressBar)
    val toolbar: Toolbar = parent.find(R.id.toolbar)
    val icon: ImageView = parent.find(R.id.icon)
    val map: ImageView = parent.find(R.id.map)
    val time: TextView = parent.find(R.id.time)
    val destination: TextView = parent.find(R.id.destination)
    val vehicle: TextView = parent.find(R.id.vehicle)
    val distance: TextView = parent.find(R.id.distance)
    val value: TextView = parent.find(R.id.value)
    var trip: TripDetail? = null

    fun bind(trip: TripDetail) {
        this.trip = trip

        toolbar.menu.clear()
        toolbar.inflateMenu(R.menu.trip_menu)
        toolbar.setOnMenuItemClickListener(this)

        progress.visibility = View.VISIBLE
        vehicle.setOnClickListener(this)
        map.setOnClickListener(this)

        time.text = String.format(context.getString(R.string.viewholder_time_text), trip.time)
        vehicle.text = String.format(context.getString(R.string.viewholder_car_text), trip.vehicle.make, trip.vehicle.model)
        destination.text = String.format(context.getString(R.string.viewholder_destination_text), trip.destination.location)

        //TODO: Move this calculation out of viewholder.
        val miles = trip.distance * 0.000621371192
        distance.text = String.format(context.getString(R.string.viewholder_miles_text), DecimalFormat("0.0").format(miles))
        value.text = String.format(context.getString(R.string.viewholder_deduction_text), DecimalFormat("0.00").format(miles * 0.54))

        icon.background = trip.vehicle.icon
        icon.setOnClickListener(this)

        Glide.with(context).load(trip.simplepath)
                .listener(this)
                .crossFade(500)
                .into(map)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.map -> {
                tripsView.showMapDetail(trip!!.key)
            }
            R.id.icon -> {
                tripsView.showVehicleDetail(trip!!.vehicle.key, String.format(context.getString(R.string.viewholder_car_text), trip!!.vehicle.make, trip!!.vehicle.model))
            }
            R.id.vehicle -> {
                tripsView.showVehicleDetail(trip!!.vehicle.key, String.format(context.getString(R.string.viewholder_car_text), trip!!.vehicle.make, trip!!.vehicle.model))
            }
        }
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_delete_trip -> {
                tripsView.deleteTrip(trip!!.key)
            }
            R.id.menu_change_vehicle -> {
                tripsView.changeVehicle(trip!!.vehicle.key)
            }
        }
        return true
    }

    override fun onException(e: Exception, model: StaticMap, target: Target<GlideDrawable>, isFirstResource: Boolean): Boolean {
        progress.visibility = View.GONE
        return false
    }

    override fun onResourceReady(resource: GlideDrawable, model: StaticMap, target: Target<GlideDrawable>, isFromMemoryCache: Boolean, isFirstResource: Boolean): Boolean {
        progress.visibility = View.GONE
        return false
    }
}
