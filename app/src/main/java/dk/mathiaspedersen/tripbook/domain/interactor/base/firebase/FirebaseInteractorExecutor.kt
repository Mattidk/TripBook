package dk.mathiaspedersen.tripbook.domain.interactor.base.firebase

interface FirebaseInteractorExecutor {
    fun execute(interactor: FirebaseInteractor, priority: FirebaseInteractorPriority = FirebaseInteractorPriority.LOW)
}