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
import dk.mathiaspedersen.tripbook.presentation.entity.TripDetail
import dk.mathiaspedersen.tripbook.presentation.helper.AppSettings
import dk.mathiaspedersen.tripbook.presentation.util.staticmaps.map.StaticMap
import jp.wasabeef.glide.transformations.CropCircleTransformation
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.ocpsoft.prettytime.PrettyTime
import org.parceler.Parcels


class ViewHolder(val context: Context, val settings: AppSettings, view: View)
    : RecyclerView.ViewHolder(view), RequestListener<StaticMap, GlideDrawable>, View.OnClickListener {

    private val progress: ProgressBar = view.find(R.id.progressBar)
    private val image: ImageView = view.find(R.id.map)
    private val carIcon: ImageView = view.find(R.id.car_icon)
    private val time: TextView = view.find(R.id.time)
    private var model: TripDetail? = null

    fun bind(model: TripDetail) {
        this.model = model

        progress.visibility = View.VISIBLE
        image.setOnClickListener(this)
        time.text = PrettyTime().format(model.time)

        val placeholder: Int?
        if (settings.isThemeDark()) {
            placeholder = R.color.knight_map_placeholder
        } else {
            placeholder = R.color.mapPlaceholder
        }

        val map = produceStaticMap(model)

        Glide.with(context).load(R.drawable.me)
                .bitmapTransform(CropCircleTransformation(context))
                .into(carIcon)

        Glide.with(context).load(map)
                .placeholder(placeholder)
                .error(R.drawable.frown_cloud)
                .listener(this)
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
        return StaticMap().path(settings.getStaticPolylineStyle(), model.map)
                .style(settings.getStaticMapStyle())
                .marker(StaticMap.Marker.Style.FLAT_GREEN.toBuilder().label('A').build(), StaticMap.GeoPoint(model.start.latitude, model.start.longitude))
                .marker(StaticMap.Marker.Style.FLAT_RED.toBuilder().label('B').build(), StaticMap.GeoPoint(model.end.latitude, model.end.longitude))
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
