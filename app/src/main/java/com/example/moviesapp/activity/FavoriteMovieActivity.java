package com.example.moviesapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.moviesapp.R;
import com.example.moviesapp.adapter.MoviesAdapter;
import com.example.moviesapp.pojo.Movie;
import com.example.moviesapp.view.model.FavoriteMovieViewModel;

import java.util.List;

public class FavoriteMovieActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_favorite_movie);

		RecyclerView rvMovies = findViewById(R.id.rvMovies);
		MoviesAdapter moviesAdapter = new MoviesAdapter();
		rvMovies.setLayoutManager(new GridLayoutManager(this, 2));
		rvMovies.setAdapter(moviesAdapter);

		moviesAdapter.setOnMovieClickListener(movie -> {
			Intent intent = MovieDetailActivity.newIntent(FavoriteMovieActivity.this, movie);
			startActivity(intent);
		});

		FavoriteMovieViewModel favoriteMovieViewModel = new ViewModelProvider(this)
				.get(FavoriteMovieViewModel.class);
		favoriteMovieViewModel.getMovies().observe(this, new Observer<List<Movie>>() {
			@Override
			public void onChanged(List<Movie> movies) {
				moviesAdapter.setMovies(movies);
			}
		});
	}

	public static Intent newIntent(Context context) {
		return new Intent(context, FavoriteMovieActivity.class);
	}
}