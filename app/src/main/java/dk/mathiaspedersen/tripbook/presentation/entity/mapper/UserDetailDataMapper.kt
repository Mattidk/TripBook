package dk.mathiaspedersen.tripbook.presentation.entity.mapper

import dk.mathiaspedersen.tripbook.domain.entity.User
import dk.mathiaspedersen.tripbook.presentation.entity.UserDetail

class UserDetailDataMapper {

    fun transformUser(user: User): UserDetail {
        return UserDetail(user.name, user.photo, user.email)
    }
}