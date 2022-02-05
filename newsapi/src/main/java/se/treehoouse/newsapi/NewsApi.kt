package se.treehoouse.newsapi

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

val json = Json {
    ignoreUnknownKeys = true
}


/**
 * Class handling the connection to news api.
 */
@OptIn(ExperimentalSerializationApi::class)
fun createNewsApiService(): NewsApiService{
    val contentType = MediaType.get("*/*")

    val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .readTimeout(120, TimeUnit.SECONDS)
        .addDebugLogging()
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("http://newsapi.org/v2/")
        .client(client)
        .addConverterFactory(json.asConverterFactory(contentType))
        .build()

    return retrofit.create(NewsApiService::class.java)
}

/**
 * Add logger if on debug build
 */
fun OkHttpClient.Builder.addDebugLogging(): OkHttpClient.Builder {
    return if(BuildConfig.DEBUG){
        val logging = HttpLoggingInterceptor()
        logging.level = Level.BODY
        this.addInterceptor(logging)
    } else {
        this
    }
}