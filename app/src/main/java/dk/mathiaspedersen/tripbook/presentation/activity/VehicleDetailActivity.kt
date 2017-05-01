package dk.mathiaspedersen.tripbook.presentation.activity

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.util.Log
import butterknife.BindView
import butterknife.ButterKnife
import dk.mathiaspedersen.tripbook.R
import dk.mathiaspedersen.tripbook.presentation.entity.VehicleDetail
import dk.mathiaspedersen.tripbook.presentation.injection.ApplicationComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.vehicledetail.VehicleDetailActivityModule
import dk.mathiaspedersen.tripbook.presentation.presenter.VehicleDetailPresenter
import dk.mathiaspedersen.tripbook.presentation.view.VehicleDetailView
import javax.inject.Inject

class VehicleDetailActivity : BaseActivity(), VehicleDetailView {
    override val layoutResource: Int = R.layout.activity_vehicle_detail

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @Inject
    lateinit var presenter: VehicleDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ButterKnife.bind(this)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun showVehicleDetails(vehicle: VehicleDetail) {
        Log.e("GODA", vehicle.make)
        Log.e("GODA", vehicle.odometer.toString())
    }

    override fun onResume() {
        super.onResume()
        getVehicle()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dispose()
    }

    fun getVehicle() {
        val key = intent.extras.getString("key")
        presenter.getVehicle(key)
    }

    override fun injectDependencies(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(VehicleDetailActivityModule(this))
                .injectTo(this)
    }
}
