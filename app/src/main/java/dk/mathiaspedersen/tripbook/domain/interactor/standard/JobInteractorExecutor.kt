package dk.mathiaspedersen.tripbook.domain.interactor.standard

interface JobInteractorExecutor {
    fun execute(interactor: JobInteractor, priority: JobInteractorPriority = JobInteractorPriority.LOW)
}