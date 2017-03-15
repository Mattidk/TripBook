package dk.mathiaspedersen.tripbook.presentation.util

import android.os.Build

fun supportsKitKat(code:() -> Unit){
    supportsVersion(code, 19)
}

fun supportsLollipop(code:() -> Unit){
    supportsVersion(code, 21)
}

fun supportsMarshmallow(code:() -> Unit){
    supportsVersion(code, 23)
}

private fun supportsVersion(code:() -> Unit, sdk: Int){
    if (Build.VERSION.SDK_INT >= sdk){
        code.invoke()
    }
}