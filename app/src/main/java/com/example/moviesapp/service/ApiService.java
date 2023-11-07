package com.example.moviesapp.service;

import com.example.moviesapp.pojo.MovieResponse;
import com.example.moviesapp.pojo.ReviewResponse;
import com.example.moviesapp.pojo.TrailerResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("v1.3/movie?token=4GV9E2F-T0FMHJS-HM4M8XH-KQ0F9XN&sortField=votes.kp&sortType=-1&limit=20&rating.imdb=8.0-10.0")
    Single<MovieResponse> loadMovies(@Query("page") int page);

    @GET("v1.4/movie/{id}?token=4GV9E2F-T0FMHJS-HM4M8XH-KQ0F9XN")
    Single<TrailerResponse> loadTrailers(@Path("id") int id);

    @GET("v1.4/review?token=4GV9E2F-T0FMHJS-HM4M8XH-KQ0F9XN&page=1&limit=10&movies.id={id}")
    Single<ReviewResponse> loaReview(@Path("id") int id);


}
