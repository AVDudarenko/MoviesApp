package com.example.moviesapp.service;

import com.example.moviesapp.pojo.MovieResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiService {
	@GET("v1.3/movie?token=4GV9E2F-T0FMHJS-HM4M8XH-KQ0F9XN&sortField=votes.kp&sortType=-1&limit=40&rating.imdb=8.0-10.0")
	Single<MovieResponse> loadMovies(@Query("page") int page);

}
