


import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/now_playing")
    suspend fun getAllMoviesNowPlaying(
        @Query("api_key") apiKey: String = "ced9627fcb3b480331b91e3223e07ac4",
        @Query("page") page: Int = 1
    ): MovieResponse

    @GET("movie/{movie_id}?api_key=ced9627fcb3b480331b91e3223e07ac4")
    suspend fun getDetailMovie(
        @Path("movie_id") movieId: Int
    ): DetailMovieResponse

}