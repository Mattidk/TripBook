package dk.mathiaspedersen.tripbook.presentation.custom

import android.content.Context
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.amulyakhare.textdrawable.TextDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import dk.mathiaspedersen.tripbook.R
import dk.mathiaspedersen.tripbook.presentation.activity.DetailActivity
import dk.mathiaspedersen.tripbook.presentation.entity.TripDetail
import dk.mathiaspedersen.tripbook.presentation.helper.AppSettings
import dk.mathiaspedersen.tripbook.presentation.util.staticmaps.map.StaticMap
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.ocpsoft.prettytime.PrettyTime
import org.parceler.Parcels
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.*


class TripViewHolder(val context: Context, val settings: AppSettings, view: View)
    : RecyclerView.ViewHolder(view), RequestListener<StaticMap, GlideDrawable>, View.OnClickListener {

    private val progress: ProgressBar = view.find(R.id.progressBar)
    private val image: ImageView = view.find(R.id.map)
    private val carIcon: ImageView = view.find(R.id.car_icon)
    private val time: TextView = view.find(R.id.time)
    private val destination : TextView = view.find(R.id.destination)
    private val carUsed : TextView = view.find(R.id.car_used)
    private val distance : TextView = view.find(R.id.distance)
    private val value : TextView = view.find(R.id.value)
    private var model: TripDetail? = null

    fun bind(model: TripDetail) {
        this.model = model

        progress.visibility = View.VISIBLE
        image.setOnClickListener(this)
        time.text = String.format(context.getString(R.string.viewholder_time_text), PrettyTime().format(Date(model.destination.timestamp * 1000)))
        carUsed.text = String.format(context.getString(R.string.viewholder_car_text), model.vehicle.make, model.vehicle.model)
        destination.text = String.format(context.getString(R.string.viewholder_destination_text), model.destination.location)

        val map = produceStaticMap(model)

        val formatMiles = DecimalFormat("#.#")
        val formatValue = DecimalFormat("#.##")
        formatMiles.roundingMode = RoundingMode.CEILING
        formatValue.roundingMode = RoundingMode.CEILING

        val miles = model.distance * 0.000621371192

        distance.text = formatMiles.format(miles)
        value.text = formatValue.format(miles * 0.54)

        val drawable = TextDrawable.builder().buildRound(model.vehicle.make[0].toString(),
                ContextCompat.getColor(context, R.color.colorLetterIcon))
        carIcon.setImageDrawable(drawable)

        Glide.with(context).load(map)
                .listener(this)
                .crossFade(500)
                .into(image)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.map -> {
                val wrapped = Parcels.wrap(model)
                context.startActivity<DetailActivity>("model" to wrapped)
            }
        }
    }

    fun produceStaticMap(model: TripDetail): StaticMap {
        return StaticMap().path(settings.getStaticPolylineStyle(), model.simplepath)
                .style(settings.getStaticMapStyle())
                .marker(StaticMap.Marker.Style.FLAT_GREEN.toBuilder().label('A').build(), StaticMap.GeoPoint(model.departure.latitude.toDouble(), model.departure.longitude.toDouble()))
                .marker(StaticMap.Marker.Style.FLAT_RED.toBuilder().label('B').build(), StaticMap.GeoPoint(model.destination.latitude.toDouble(), model.destination.longitude.toDouble()))
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
