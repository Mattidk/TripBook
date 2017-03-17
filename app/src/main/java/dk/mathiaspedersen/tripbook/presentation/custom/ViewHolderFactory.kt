package dk.mathiaspedersen.tripbook.presentation.custom

import android.content.Context
import android.view.ViewGroup
import dk.mathiaspedersen.tripbook.presentation.helper.AppSettings


class ViewHolderFactory(val context: Context, val settings: AppSettings) {

    fun create(parent: ViewGroup): ViewHolder {
        return ViewHolder(context, settings, parent)
    }
}