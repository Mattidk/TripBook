package dk.mathiaspedersen.tripbook.domain.interactor.event

import dk.mathiaspedersen.tripbook.domain.entity.User

class GetProfileSuccessEvent(val user: User): Event