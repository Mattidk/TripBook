package dk.mathiaspedersen.tripbook.domain.interactor.firebase

class FirebaseInteractorExecutorImpl: FirebaseInteractorExecutor {

    override fun execute(interactor: FirebaseInteractor) {
        interactor.invoke()
    }
}