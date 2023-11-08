package com.example.moviesapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviesapp.R;
import com.example.moviesapp.adapter.MoviesAdapter;
import com.example.moviesapp.view.model.MainViewModel;

public class MainActivity extends AppCompatActivity {

	private MainViewModel mainViewModel;
	private RecyclerView recyclerViewMovies;
	private ProgressBar pbLoading;
	private MoviesAdapter moviesAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initViews();

		moviesAdapter = new MoviesAdapter();
		recyclerViewMovies.setAdapter(moviesAdapter);
		recyclerViewMovies.setLayoutManager(new GridLayoutManager(this, 2));

		mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
		mainViewModel.getMoviesLiveData().observe(
				this,
				movies -> moviesAdapter.setMovies(movies));
		mainViewModel.getIsLoading().observe(
				this,
				isLoading -> {
					if (isLoading) {
						pbLoading.setVisibility(View.VISIBLE);
					} else {
						pbLoading.setVisibility(View.GONE);
					}
				});
		moviesAdapter.setOnReachEndListener(() -> mainViewModel.loadMovies());
		moviesAdapter.setOnMovieClickListener(movie -> {
			Intent intent = MovieDetailActivity.newIntent(MainActivity.this, movie);
			startActivity(intent);
		});
	}

	private void initViews() {
		recyclerViewMovies = findViewById(R.id.rvMovies);
		pbLoading = findViewById(R.id.pbLoading);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
		if (item.getItemId() == R.id.item_favorites) {
			Intent intent = FavoriteMovieActivity.newIntent(MainActivity.this);
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}
}