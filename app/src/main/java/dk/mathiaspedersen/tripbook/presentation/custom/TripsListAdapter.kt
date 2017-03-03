package dk.mathiaspedersen.tripbook.presentation.custom

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import dk.mathiaspedersen.tripbook.R
import dk.mathiaspedersen.tripbook.presentation.activity.DetailActivity
import dk.mathiaspedersen.tripbook.presentation.entity.TripDetail
import dk.mathiaspedersen.tripbook.presentation.util.maps.map.Map
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class TripsListAdapter(var trips: List<TripDetail>)
    : RecyclerView.Adapter<TripsListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return TripsListAdapter.ViewHolder(parent.context, LayoutInflater.from(parent.context)
                .inflate(R.layout.item_trip, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(trips[position])
    }

    override fun getItemCount(): Int = trips.size

    fun refresh(list: List<TripDetail>) {
        trips = list
        notifyDataSetChanged()
    }

    class ViewHolder(val context: Context, view: View) : RecyclerView.ViewHolder(view), RequestListener<Map, GlideDrawable>, View.OnClickListener {

        private val progress: ProgressBar = view.find(R.id.progressBar)
        private val image: ImageView = view.find(R.id.map)


        fun bind(model: TripDetail) {
            progress.visibility = View.VISIBLE

            image.setOnClickListener(this)

            Glide.with(itemView.context).load(model.map)
                    .placeholder(R.color.placeholder_background)
                    .error(R.drawable.frown_cloud)
                    .listener(this)
                    .into(image)
        }

        override fun onClick(view: View) {
            when (view.id) {
                R.id.map -> context.startActivity<DetailActivity>()
            }
        }

        override fun onException(e: Exception?, model: Map, target: Target<GlideDrawable>, isFirstResource: Boolean): Boolean {
            progress.visibility = View.GONE
            return false
        }

        override fun onResourceReady(resource: GlideDrawable?, model: Map, target: Target<GlideDrawable>, isFromMemoryCache: Boolean, isFirstResource: Boolean): Boolean {
            progress.visibility = View.GONE
            return false
        }
    }
}