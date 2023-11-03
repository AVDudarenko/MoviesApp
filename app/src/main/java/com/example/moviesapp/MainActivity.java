package com.example.moviesapp;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.moviesapp.view.model.MainViewModel;

public class MainActivity extends AppCompatActivity {

	private MainViewModel mainViewModel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
		mainViewModel.getMoviesLiveData().observe(
				this,
				movies -> Log.d("MainActivity", movies.toString())
		);
		mainViewModel.loadMovies();
	}
}