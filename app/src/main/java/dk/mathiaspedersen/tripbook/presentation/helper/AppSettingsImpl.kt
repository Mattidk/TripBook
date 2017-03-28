package dk.mathiaspedersen.tripbook.presentation.helper

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.graphics.Color
import android.os.Vibrator
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolylineOptions
import dk.mathiaspedersen.tripbook.R
import dk.mathiaspedersen.tripbook.presentation.util.staticmaps.map.StaticMap


class AppSettingsImpl(val context: Context, val preferences: SharedPreferences) : AppSettings {

    override fun vibrateOnClassification(): Boolean {
        val setting = preferences.getBoolean("pref_key_vibrate_on_classification", false)
        val v = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        return setting and v.hasVibrator()
    }

    override fun isThemeDark(): Boolean {
        return preferences.getBoolean("pref_key_dark_theme", false)
    }

    override fun getStaticMapStyle(): String {

        val currentNightMode = context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK

        when (currentNightMode) {
            Configuration.UI_MODE_NIGHT_NO -> {
                return context.getString(R.string.iovation)
            }
            Configuration.UI_MODE_NIGHT_YES -> {
                return context.getString(R.string.cobolt)
            }
            else -> {
                return context.getString(R.string.iovation)
            }
        }
    }

    override fun getStaticPolylineStyle(): StaticMap.Path.Style {

        val currentNightMode = context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK

        when (currentNightMode) {
            Configuration.UI_MODE_NIGHT_NO -> {
                return StaticMap.Path.Style.builder().color(Color.BLUE).build()
            }
            Configuration.UI_MODE_NIGHT_YES -> {
                return StaticMap.Path.Style.builder().stroke(3).color(Color.WHITE).build()
            }
            else -> {
                return StaticMap.Path.Style.builder().color(Color.BLUE).build()
            }
        }
    }

    override fun getMapStyle(): Int {

        val currentNightMode = context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK

        when (currentNightMode) {
            Configuration.UI_MODE_NIGHT_NO -> {
                return R.raw.iovation
            }
            Configuration.UI_MODE_NIGHT_YES -> {
                return R.raw.cobolt
            }
            else -> {
                return R.raw.iovation
            }
        }
    }

    override fun getPolylineStyle(path: List<LatLng>): PolylineOptions {

        val currentNightMode = context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK

        when (currentNightMode) {
            Configuration.UI_MODE_NIGHT_NO -> {
                return PolylineOptions().addAll(path).width(10.toFloat()).color(Color.BLUE)
            }
            Configuration.UI_MODE_NIGHT_YES -> {
                return PolylineOptions().addAll(path).width(10.toFloat()).color(Color.WHITE)
            }
            else -> {
                return PolylineOptions().addAll(path).width(10.toFloat()).color(Color.BLUE)
            }
        }
    }

    override fun preventExit(): Boolean {
        return preferences.getBoolean("pref_key_prevent_accidental_exit", false)
    }
}
