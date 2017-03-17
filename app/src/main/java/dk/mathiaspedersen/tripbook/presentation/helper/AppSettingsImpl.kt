package dk.mathiaspedersen.tripbook.presentation.helper

import android.content.Context
import android.content.SharedPreferences
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

    override fun getTranslucentTheme(): Int {
        val setting = preferences.getBoolean("pref_key_dark_theme", false)

        when (setting) {
            true -> return R.style.KnightRider_Translucent
            false -> return R.style.AppTheme_Translucent
        }
    }

    override fun getStaticMapStyle(): String {
        val setting = preferences.getBoolean("pref_key_dark_theme", false)

        when (setting) {
            true -> return context.getString(R.string.cobolt)
            false -> return context.getString(R.string.iovation)
        }
    }

    override fun getStaticPolylineStyle(): StaticMap.Path.Style {
        val setting = preferences.getBoolean("pref_key_dark_theme", false)

        when (setting) {
            true -> return StaticMap.Path.Style.builder().stroke(3).color(Color.WHITE).build()
            false -> return StaticMap.Path.Style.builder().color(Color.BLUE).build()
        }
    }

    override fun getMapStyle(): Int {
        val setting = preferences.getBoolean("pref_key_dark_theme", false)

        when (setting) {
            true -> return R.raw.cobolt
            false -> return R.raw.iovation
        }
    }

    override fun getPolylineStyle(path: MutableList<LatLng>): PolylineOptions {
        val setting = preferences.getBoolean("pref_key_dark_theme", false)

        when (setting) {
            true -> return PolylineOptions().addAll(path).width(10.toFloat()).color(Color.WHITE)
            false -> return PolylineOptions().addAll(path).width(10.toFloat()).color(Color.BLUE)
        }
    }

    override fun getSelectedTheme(): Int {
        val setting = preferences.getBoolean("pref_key_dark_theme", false)

        when (setting) {
            true -> return R.style.KnightRider
            false -> return R.style.AppTheme
        }
    }

    override fun preventExit(): Boolean {
        return preferences.getBoolean("pref_key_prevent_accidental_exit", false)
    }
}