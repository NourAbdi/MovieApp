package net.androidsquad.movieapp.utils;

import net.androidsquad.movieapp.models.MovieModel;
import net.androidsquad.movieapp.response.MovieResponse;
import net.androidsquad.movieapp.response.MovieSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {

    //Search for movies
    //https://api.themoviedb.org/3/search/movie
    @GET("/3/search/movie")
    Call<MovieSearchResponse> searchMovie(
            @Query("api_key") String key,
            @Query("query") String query,
            @Query("page") int page
    );

    // https://api.themoviedb.org/3/movie/popular?api_key=647f449dbbd76d6edac75fbc67d2e870&language=en-US&page=1
    @GET("/3/movie/popular")
    Call<MovieSearchResponse> getPopular(
            @Query("api_key") String key,
            @Query("page") int page
    );

    // Making Search with ID
    // https://api.themoviedb.org/3/movie/{Movie_ID}?api_key=647f449dbbd76d6edac75fbc67d2e870
    @GET("3/movie/{movie_id}?")
    Call<MovieModel> getMovie(
            @Path("movie_id") int movie_id,
            @Query("api_key") String api_key
    );
}
