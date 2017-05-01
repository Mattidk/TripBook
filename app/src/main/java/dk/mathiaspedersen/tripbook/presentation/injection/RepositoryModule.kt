package dk.mathiaspedersen.tripbook.presentation.injection

import dagger.Module
import dagger.Provides
import dk.mathiaspedersen.tripbook.data.entity.mapper.TripMapper
import dk.mathiaspedersen.tripbook.data.entity.mapper.VehicleMapper
import dk.mathiaspedersen.tripbook.data.repository.TripDataRepository
import dk.mathiaspedersen.tripbook.data.repository.VehicleDataRepository
import dk.mathiaspedersen.tripbook.data.repository.datasource.TripDataSource
import dk.mathiaspedersen.tripbook.data.repository.datasource.VehicleDataSource
import dk.mathiaspedersen.tripbook.domain.repository.TripRepository
import dk.mathiaspedersen.tripbook.domain.repository.VehicleRepository
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    fun provideTripMapper() = TripMapper()

    @Provides
    fun provideVehicleMapper() = VehicleMapper()

    @Provides @Singleton
    fun provideTripRepository(datasource: TripDataSource, mapper: TripMapper): TripRepository
                              = TripDataRepository(datasource, mapper)

    @Provides @Singleton
    fun provideVehicleRepository(datasource: VehicleDataSource, mapper: VehicleMapper): VehicleRepository
            = VehicleDataRepository(datasource, mapper)

}