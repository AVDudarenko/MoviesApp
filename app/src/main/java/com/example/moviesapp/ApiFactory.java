package com.example.moviesapp;

import com.example.moviesapp.service.ApiService;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiFactory {

	private static final String BASE_URL = "https://api.kinopoisk.dev/";
	private static final Retrofit retroft = new Retrofit.Builder()
			.baseUrl(BASE_URL)
			.addConverterFactory(GsonConverterFactory.create())
			.addCallAdapterFactory(RxJava3CallAdapterFactory.create())
			.build();

	public static final ApiService apiService = retroft.create(ApiService.class);

}
