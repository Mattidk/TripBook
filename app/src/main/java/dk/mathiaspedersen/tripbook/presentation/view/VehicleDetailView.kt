package dk.mathiaspedersen.tripbook.presentation.view

import dk.mathiaspedersen.tripbook.presentation.entity.VehicleDetail

interface VehicleDetailView: BaseView{
    fun showVehicleDetails(vehicle: VehicleDetail)
}