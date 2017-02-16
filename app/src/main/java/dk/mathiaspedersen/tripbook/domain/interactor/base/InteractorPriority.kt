package dk.mathiaspedersen.tripbook.domain.interactor.base

enum class InteractorPriority(val value: Int) {
    LOW(0),
    MID(500),
    HIGH(1000)
}