package com.example.moviesapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviesapp.adapter.MoviesAdapter;
import com.example.moviesapp.view.model.MainViewModel;

public class MainActivity extends AppCompatActivity {

	private MainViewModel mainViewModel;
	private RecyclerView recyclerViewMovies;
	private MoviesAdapter moviesAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		recyclerViewMovies = findViewById(R.id.rvMovies);
		moviesAdapter = new MoviesAdapter();
		recyclerViewMovies.setAdapter(moviesAdapter);
		recyclerViewMovies.setLayoutManager(new GridLayoutManager(this, 2));

		mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
		mainViewModel.getMoviesLiveData().observe(
				this,
				movies -> moviesAdapter.setMovies(movies));
		mainViewModel.loadMovies();
	}
}