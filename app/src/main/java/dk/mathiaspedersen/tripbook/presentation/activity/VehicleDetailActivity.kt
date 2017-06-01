package dk.mathiaspedersen.tripbook.presentation.activity

import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.widget.Toolbar
import android.util.Log
import android.widget.RelativeLayout
import butterknife.BindView
import butterknife.ButterKnife
import com.amulyakhare.textdrawable.util.ColorGenerator
import dk.mathiaspedersen.tripbook.R
import dk.mathiaspedersen.tripbook.presentation.entity.VehicleDetail
import dk.mathiaspedersen.tripbook.presentation.injection.ApplicationComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.vehicledetail.VehicleDetailActivityModule
import dk.mathiaspedersen.tripbook.presentation.presenter.VehicleDetailPresenter
import dk.mathiaspedersen.tripbook.presentation.view.VehicleDetailView
import org.jetbrains.anko.backgroundColor
import javax.inject.Inject

class VehicleDetailActivity : BaseActivity(), VehicleDetailView {
    override val layoutResource: Int = R.layout.activity_vehicle_detail

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.header_content)
    lateinit var headerContent: RelativeLayout

    @BindView(R.id.toolbar_layout)
    lateinit var toolbarLayout: CollapsingToolbarLayout

    @Inject
    lateinit var presenter: VehicleDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ButterKnife.bind(this)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        setupHeader()
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

    fun setupHeader() {
        val name = intent.extras.getString("name")
        val letter = name[0].toString()
        val color = ColorGenerator.MATERIAL.getColor(letter)
        headerContent.backgroundColor = color
        toolbarLayout.setContentScrimColor(color)
        toolbar.backgroundColor = color
        title = name
    }

    override fun injectDependencies(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(VehicleDetailActivityModule(this))
                .injectTo(this)
    }
}