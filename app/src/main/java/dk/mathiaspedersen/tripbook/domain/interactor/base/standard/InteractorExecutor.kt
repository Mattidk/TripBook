package dk.mathiaspedersen.tripbook.domain.interactor.base.standard

interface InteractorExecutor {
    fun execute(interactor: Interactor, priority: InteractorPriority = InteractorPriority.LOW)
}