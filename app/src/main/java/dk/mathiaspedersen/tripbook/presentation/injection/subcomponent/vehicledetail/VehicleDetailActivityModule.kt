package dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.vehicledetail

import dagger.Module
import dagger.Provides
import dk.mathiaspedersen.tripbook.domain.interactor.GetVehicle
import dk.mathiaspedersen.tripbook.presentation.activity.VehicleDetailActivity
import dk.mathiaspedersen.tripbook.presentation.entity.mapper.VehicleDetailDataMapper
import dk.mathiaspedersen.tripbook.presentation.injection.ActivityModule
import dk.mathiaspedersen.tripbook.presentation.injection.scope.ActivityScope
import dk.mathiaspedersen.tripbook.presentation.presenter.VehicleDetailPresenter
import dk.mathiaspedersen.tripbook.presentation.view.VehicleDetailView

@Module
class VehicleDetailActivityModule(activity: VehicleDetailActivity) : ActivityModule(activity) {

    @Provides @ActivityScope
    fun provideVehicleDetailView(): VehicleDetailView = activity as VehicleDetailView

    @Provides @ActivityScope
    fun provideVehicleDataMapper() = VehicleDetailDataMapper()

    @Provides @ActivityScope
    fun provideVehicleDetailPresenter(view: VehicleDetailView, getVehicle: GetVehicle, mapper: VehicleDetailDataMapper)
                                      = VehicleDetailPresenter(view, getVehicle, mapper)
}