import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://functions.yandexcloud.net/d4e2bt6jba6cmiekqmsv/"

    private val okHttpClient = OkHttpClient.Builder()
        .build()

    val instance: IpApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
            .create(IpApiService::class.java)
    }
}