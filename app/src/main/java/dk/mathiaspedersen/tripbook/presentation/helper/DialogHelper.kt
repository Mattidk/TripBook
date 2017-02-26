package dk.mathiaspedersen.tripbook.presentation.helper

import android.app.ProgressDialog
import android.content.Context

class DialogHelper(val context: Context) {

    val dialog = ProgressDialog(context)

    fun showProgressDialog(message: Int) {
        if (!dialog.isShowing){
            dialog.setMessage(context.getString(message))
            dialog.isIndeterminate = true
            dialog.setCanceledOnTouchOutside(false)
            dialog.show()
        }
    }

    fun showProgressDialog(message: Int, title: Int) {
        if (!dialog.isShowing){
            dialog.setTitle(context.getString(title))
            dialog.setMessage(context.getString(message))
            dialog.isIndeterminate = true
            dialog.setCanceledOnTouchOutside(false)
            dialog.show()
        }
    }

    fun hideProgressDialog() {
        if (dialog.isShowing){
            dialog.dismiss()
        }
    }
}