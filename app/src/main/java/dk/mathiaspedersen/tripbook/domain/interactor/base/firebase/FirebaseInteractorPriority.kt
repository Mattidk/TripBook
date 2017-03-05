package dk.mathiaspedersen.tripbook.domain.interactor.base.firebase

enum class FirebaseInteractorPriority(val value: Int) {
    LOW(0),
    MID(500),
    HIGH(1000)
}