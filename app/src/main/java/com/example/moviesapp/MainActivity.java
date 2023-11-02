package com.example.moviesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import com.example.moviesapp.pojo.MovieResponse;
import com.example.moviesapp.service.ApiService;
import com.example.moviesapp.view.model.MainViewModel;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

	private MainViewModel mainViewModel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
		mainViewModel.getMoviesLiveData().observe(
				this,
				movies -> Log.d("MAinACtivity", movies.toString())
		);
		mainViewModel.loadMovies();
	}
}