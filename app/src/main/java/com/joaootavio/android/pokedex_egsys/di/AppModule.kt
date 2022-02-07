package com.joaootavio.android.pokedex_egsys.di

import com.joaootavio.android.pokedex_egsys.common.Constants.BASE_URL
import com.joaootavio.android.pokedex_egsys.data.remote.PokemonApi
import com.joaootavio.android.pokedex_egsys.data.repository.PokemonRepositoryImpl
import com.joaootavio.android.pokedex_egsys.domain.repository.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePokemonApi(): PokemonApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokemonApi::class.java)
    }

    @Provides
    @Singleton
    fun providePokemonRepository(api: PokemonApi): PokemonRepository {
        return PokemonRepositoryImpl(api = api)
    }

}