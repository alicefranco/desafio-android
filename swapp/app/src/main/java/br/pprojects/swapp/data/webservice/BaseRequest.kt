package br.pprojects.swapp.data.webservice

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory


object BaseRequest {
    private const val BASE_URL_SWAPI = "https://swapi.co/"
    private const val BASE_URL_POPCODE = "http://private-782d3-starwarsfavorites.apiary-mock.com/"

    private val loggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    private val interceptor = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    val serviceSwapi = Retrofit
        .Builder()
        .baseUrl(BASE_URL_SWAPI)
        .addConverterFactory(JacksonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(interceptor)
        .build()

    val servicePopCode = Retrofit
            .Builder()
            .baseUrl(BASE_URL_POPCODE)
            .addConverterFactory(JacksonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(interceptor)
            .build()
}
