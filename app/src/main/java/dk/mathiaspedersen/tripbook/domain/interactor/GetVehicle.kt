package dk.mathiaspedersen.tripbook.domain.interactor

import dk.mathiaspedersen.tripbook.domain.entity.Vehicle
import dk.mathiaspedersen.tripbook.domain.interactor.base.BaseUseCase
import dk.mathiaspedersen.tripbook.domain.interactor.base.Params
import dk.mathiaspedersen.tripbook.domain.repository.VehicleRepository
import io.reactivex.Observable

class GetVehicle(val repository: VehicleRepository) : BaseUseCase() {

    override fun getObservable(params: Params): Observable<Vehicle> {
        val key = params.getString("key", null)
        return repository.getVehicle(key)
    }
}