package dk.mathiaspedersen.tripbook.presentation.util

import android.content.Context
import android.content.res.Resources
import android.graphics.Point
import android.os.Build
import android.view.WindowManager

private var screenWidth = 0
private var screenHeight = 0

fun dpToPx(dp: Int): Int {
    return (dp * Resources.getSystem().displayMetrics.density).toInt()
}

fun getScreenHeight(c: Context): Int {
    if (screenHeight == 0) {
        val wm = c.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        val size = Point()
        display.getSize(size)
        screenHeight = size.y
    }

    return screenHeight
}

fun getScreenWidth(c: Context): Int {
    if (screenWidth == 0) {
        val wm = c.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        val size = Point()
        display.getSize(size)
        screenWidth = size.x
    }

    return screenWidth
}

fun isAndroid5(): Boolean {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
}
