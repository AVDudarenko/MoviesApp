package com.example.moviesapp.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviesapp.ApiFactory;
import com.example.moviesapp.R;
import com.example.moviesapp.adapter.ReviewsAdapter;
import com.example.moviesapp.adapter.TrailerAdapter;
import com.example.moviesapp.pojo.Movie;
import com.example.moviesapp.pojo.ReviewResponse;
import com.example.moviesapp.view.model.MovieDetailViewModel;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MovieDetailActivity extends AppCompatActivity {
	private ImageView ivPoster;
	private TextView tvTitle;
	private TextView tvYear;
	private TextView tvDescription;
	private RecyclerView rvTrailers;
	private RecyclerView rvReviews;
	private TrailerAdapter trailerAdapter;
	private ReviewsAdapter reviewsAdapter;

	private static final String EXTRA_MOVIE = "movie";
	private static final String TAG = "MovieDetailActivity";
	private MovieDetailViewModel movieDetailViewModel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_movie_detail);
		movieDetailViewModel = new ViewModelProvider(this).get(MovieDetailViewModel.class);
		initViews();

		trailerAdapter = new TrailerAdapter();
		reviewsAdapter = new ReviewsAdapter();
		rvTrailers.setAdapter(trailerAdapter);
		rvReviews.setAdapter(reviewsAdapter);

		Movie movie = (Movie) getIntent().getSerializableExtra(EXTRA_MOVIE);

		Glide.with(this)
				.load(movie.getPoster().getUrl())
				.into(ivPoster);
		tvTitle.setText(movie.getName());
		tvYear.setText(String.valueOf(movie.getYear()));
		tvDescription.setText(movie.getDescription());

		movieDetailViewModel.loadTrailers(movie.getId());
		movieDetailViewModel.getTrailersMutableLiveData().observe(
				this,
				trailers -> trailerAdapter.setTrailers(trailers)
		);
		trailerAdapter.setOnTrailerClickListener(trailer -> {
			Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.setData(Uri.parse(trailer.getUrl()));
			startActivity(intent);
		});

		movieDetailViewModel.loadReviews(movie.getId());
		movieDetailViewModel.getReviewsMutableLiveData().observe(
				this,
				reviewsList -> reviewsAdapter.setReviews(reviewsList)
		);

	}

	private void initViews() {
		ivPoster = findViewById(R.id.ivPoster);
		tvTitle = findViewById(R.id.tvTitle);
		tvYear = findViewById(R.id.tvYear);
		tvDescription = findViewById(R.id.tvDescription);
		rvTrailers = findViewById(R.id.rvTrailers);
		rvReviews = findViewById(R.id.rvReviews);
	}

	public static Intent newIntent(Context context, Movie movie) {
		Intent intent = new Intent(context, MovieDetailActivity.class);
		intent.putExtra(EXTRA_MOVIE, movie);
		return intent;
	}
}