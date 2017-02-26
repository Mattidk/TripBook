package dk.mathiaspedersen.tripbook.presentation.helper

import android.content.Context

class ViewHelperImpl(val context: Context, val helper: DialogHelper) : ViewHelper {

    override fun showProgress(message: Int) {
        helper.showProgressDialog(message)
    }

    override fun showProgress(message: Int, title: Int) {
        throw UnsupportedOperationException("not implemented")
    }

    override fun showMessage(message: Int) {
        throw UnsupportedOperationException("not implemented")
    }

    override fun showMessage(message: Int, title: Int) {
        throw UnsupportedOperationException("not implemented")
    }

    override fun hideProgress() {
        helper.hideProgressDialog()
    }

    override fun hideKeyboard() {
        throw UnsupportedOperationException("not implemented")
    }
}