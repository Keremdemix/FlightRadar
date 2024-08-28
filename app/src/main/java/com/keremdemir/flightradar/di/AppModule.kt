package com.keremdemir.flightradar.di

import com.keremdemir.flightradar.data.repository.FlightsRepository
import com.keremdemir.flightradar.data.repository.FlightsRepositoryImpl
import com.keremdemir.flightradar.data.service.FlightsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideRepository(
        flightsApi: FlightsApi
    ): FlightsRepository = FlightsRepositoryImpl(flightsApi)


    private const val BASE_URL = "https://api.schiphol.nl/public-flights/"
    @Provides
    fun provideFlightApi(): FlightsApi {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(FlightsApi::class.java)
    }
}