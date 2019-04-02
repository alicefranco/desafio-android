package br.pprojects.swapp.data.webservice

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory


object BaseRequest {
    private const val BASE_URL = "https://swapi.co/"

    private val loggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    private val interceptor = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    val service = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(JacksonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(interceptor)
        .build()
}
