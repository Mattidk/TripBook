package dk.mathiaspedersen.tripbook.domain.interactor.firebase

interface FirebaseInteractor {
    operator fun invoke()
    fun successful(result: String)
    fun unsuccessful(error: String)
}