package com.example.doglist.di

import com.example.doglist.BuildConfig
import com.example.doglist.data.network.DogService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun providesClient(moshi: Moshi): DogService {
        val client = OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) {
                val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }
                addInterceptor(logger)
            }
        }.build()

        val moshiFactory = MoshiConverterFactory.create(moshi)

        return Retrofit.Builder()
            .baseUrl("https://jsonblob.com/")
            .client(client)
            .addConverterFactory(moshiFactory)
            .build()
            .create(DogService::class.java)
    }

    @Provides
    fun providesMoshi(): Moshi {
        return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }
}