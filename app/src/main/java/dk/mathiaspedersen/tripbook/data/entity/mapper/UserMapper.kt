package dk.mathiaspedersen.tripbook.data.entity.mapper

import dk.mathiaspedersen.tripbook.data.entity.UserEntity
import dk.mathiaspedersen.tripbook.domain.entity.User

class UserMapper {

    fun transform(user: UserEntity): User {
        return User(user.name, user.photo, user.email)
    }
}