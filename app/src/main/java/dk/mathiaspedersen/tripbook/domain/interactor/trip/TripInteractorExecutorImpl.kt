package dk.mathiaspedersen.tripbook.domain.interactor.trip

class TripInteractorExecutorImpl : TripInteractorExecutor {

    override fun getTrips(interactor: TripInteractor) {
        interactor.getTrips()
    }
}