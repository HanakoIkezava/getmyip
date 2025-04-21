import retrofit2.http.GET

interface IpApiService {
    @GET(".")
    suspend fun getIpAddress(): String
}