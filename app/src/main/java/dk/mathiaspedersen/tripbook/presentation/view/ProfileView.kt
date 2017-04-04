package dk.mathiaspedersen.tripbook.presentation.view

import dk.mathiaspedersen.tripbook.presentation.entity.UserDetail

interface ProfileView: BaseView {
    fun onGetProfileSuccess(user: UserDetail)
    fun onGetProfileFailure(message: String)
}