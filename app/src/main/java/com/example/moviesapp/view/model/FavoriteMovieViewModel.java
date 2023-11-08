package com.example.moviesapp.view.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.moviesapp.db.MovieDAO;
import com.example.moviesapp.db.MovieDatabase;
import com.example.moviesapp.pojo.Movie;

import java.util.List;

public class FavoriteMovieViewModel extends AndroidViewModel {

	private final MovieDAO movieDAO;

	public FavoriteMovieViewModel(@NonNull Application application) {
		super(application);
		movieDAO = MovieDatabase.getInstance(application).movieDAO();
	}

	public LiveData<List<Movie>> getMovies() {
		return movieDAO.getAllFavoriteMovies();
	}
}
