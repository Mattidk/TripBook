package dk.mathiaspedersen.tripbook.presentation.custom

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import dk.mathiaspedersen.tripbook.R
import dk.mathiaspedersen.tripbook.presentation.activity.DetailActivity
import dk.mathiaspedersen.tripbook.presentation.activity.VehicleDetailActivity
import dk.mathiaspedersen.tripbook.presentation.entity.TripDetail
import dk.mathiaspedersen.tripbook.presentation.helper.AppSettings
import dk.mathiaspedersen.tripbook.presentation.util.staticmaps.map.StaticMap
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import java.text.DecimalFormat


class TripViewHolder(val context: Context, val settings: AppSettings, val view: View)
    : RecyclerView.ViewHolder(view), RequestListener<StaticMap, GlideDrawable>, View.OnClickListener {

    val progress: ProgressBar = view.find(R.id.progressBar)
    val icon: ImageView = view.find(R.id.icon)
    val map: ImageView = view.find(R.id.map)
    val time: TextView = view.find(R.id.time)
    val destination: TextView = view.find(R.id.destination)
    val vehicle: TextView = view.find(R.id.vehicle)
    val distance: TextView = view.find(R.id.distance)
    val value: TextView = view.find(R.id.value)
    var trip: TripDetail? = null

    fun bind(trip: TripDetail) {
        this.trip = trip

        progress.visibility = View.VISIBLE
        map.setOnClickListener(this)

        time.text = String.format(context.getString(R.string.viewholder_time_text), trip.time)
        vehicle.text = String.format(context.getString(R.string.viewholder_car_text), trip.vehicle.make, trip.vehicle.model)
        destination.text = String.format(context.getString(R.string.viewholder_destination_text), trip.destination.location)

        //TODO: Move this calculation out of viewholder.
        val miles = trip.distance * 0.000621371192
        distance.text = String.format(context.getString(R.string.viewholder_miles_text), DecimalFormat("0.0").format(miles))
        value.text = String.format(context.getString(R.string.viewholder_deduction_text), DecimalFormat("0.00").format(miles * 0.54))

        icon.setImageDrawable(trip.vehicle.icon)
        icon.setOnClickListener(this)

        Glide.with(context).load(trip.simplepath)
                .listener(this)
                .crossFade(500)
                .into(map)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.map -> {
                context.startActivity<DetailActivity>("key" to trip!!.key)
            }
            R.id.icon -> {
                context.startActivity<VehicleDetailActivity>("key" to trip!!.vehicle.key)
            }
        }
    }

    override fun onException(e: Exception?, model: StaticMap, target: Target<GlideDrawable>, isFirstResource: Boolean): Boolean {
        progress.visibility = View.GONE
        return false
    }

    override fun onResourceReady(resource: GlideDrawable?, model: StaticMap, target: Target<GlideDrawable>, isFromMemoryCache: Boolean, isFirstResource: Boolean): Boolean {
        progress.visibility = View.GONE
        return false
    }
}
