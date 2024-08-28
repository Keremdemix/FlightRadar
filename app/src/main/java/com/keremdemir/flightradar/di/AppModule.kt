package com.keremdemir.flightradar.di

import com.keremdemir.flightradar.data.repository.FlightsRepository
import com.keremdemir.flightradar.data.repository.FlightsRepositoryImpl
import com.keremdemir.flightradar.data.service.FlightsApi
import com.keremdemir.flightradar.data.service.RetrofitInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideFlightsApi(): FlightsApi {
        return RetrofitInstance.api
    }

    @Provides
    fun provideRepository(
        flightsApi: FlightsApi
    ): FlightsRepository = FlightsRepositoryImpl(flightsApi)
}
