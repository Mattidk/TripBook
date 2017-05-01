package dk.mathiaspedersen.tripbook.presentation.presenter

import dk.mathiaspedersen.tripbook.domain.entity.Vehicle
import dk.mathiaspedersen.tripbook.domain.interactor.GetVehicle
import dk.mathiaspedersen.tripbook.domain.interactor.base.DefaultObserver
import dk.mathiaspedersen.tripbook.domain.interactor.base.Params
import dk.mathiaspedersen.tripbook.presentation.entity.mapper.VehicleDetailDataMapper
import dk.mathiaspedersen.tripbook.presentation.view.VehicleDetailView

class VehicleDetailPresenter(override val view: VehicleDetailView, val getVehicle: GetVehicle,
                             val mapper: VehicleDetailDataMapper) : BasePresenter<VehicleDetailView> {

    fun getVehicle(key: String) {
        val params = Params.create()
        params.putString("key", key)
        getVehicle.execute(GetVehicleObserver(), params)
    }

    override fun dispose() {
        getVehicle.dispose()
    }

    inner class GetVehicleObserver : DefaultObserver<Vehicle>() {
        override fun onNext(t: Vehicle) {
            super.onNext(t)
            view.showVehicleDetails(mapper.transform(t))
        }

        override fun onError(exception: Throwable) {
            super.onError(exception)
        }
    }
}