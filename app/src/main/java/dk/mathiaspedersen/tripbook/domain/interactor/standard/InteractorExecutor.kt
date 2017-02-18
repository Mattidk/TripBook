package dk.mathiaspedersen.tripbook.domain.interactor.standard

interface InteractorExecutor {
    fun execute(interactor: Interactor, priority: InteractorPriority = InteractorPriority.LOW)
}