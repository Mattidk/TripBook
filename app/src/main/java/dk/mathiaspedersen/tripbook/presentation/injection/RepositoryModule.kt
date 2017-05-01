package dk.mathiaspedersen.tripbook.presentation.injection

import dagger.Module
import dagger.Provides
import dk.mathiaspedersen.tripbook.data.entity.mapper.TripMapper
import dk.mathiaspedersen.tripbook.data.repository.TripDataRepository
import dk.mathiaspedersen.tripbook.data.repository.datasource.TripDataSource
import dk.mathiaspedersen.tripbook.domain.repository.TripRepository
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    fun provideTripMapper() = TripMapper()

    @Provides @Singleton
    fun provideTripRepository(datasource: TripDataSource, mapper: TripMapper): TripRepository
                              = TripDataRepository(datasource, mapper)

}