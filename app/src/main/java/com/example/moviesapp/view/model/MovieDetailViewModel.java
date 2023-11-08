package com.example.moviesapp.view.model;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.moviesapp.ApiFactory;
import com.example.moviesapp.db.MovieDAO;
import com.example.moviesapp.db.MovieDatabase;
import com.example.moviesapp.pojo.Movie;
import com.example.moviesapp.pojo.Review;
import com.example.moviesapp.pojo.ReviewResponse;
import com.example.moviesapp.pojo.Trailer;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MovieDetailViewModel extends AndroidViewModel {
	private static final String TAG = "MovieDetailViewModel";
	private final MovieDAO movieDAO;
	private final CompositeDisposable compositeDisposable = new CompositeDisposable();
	private final MutableLiveData<List<Trailer>> trailersMutableLiveData = new MutableLiveData<>();
	private final MutableLiveData<List<Review>> reviewsMutableLiveData = new MutableLiveData<>();

	public LiveData<List<Trailer>> getTrailersMutableLiveData() {
		return trailersMutableLiveData;
	}

	public LiveData<List<Review>> getReviewsMutableLiveData() {
		return reviewsMutableLiveData;
	}

	/**
	 * method for load trailers
	 *
	 * @param id - contains film id
	 */
	public void loadTrailers(int id) {
		Disposable disposable = ApiFactory.apiService.loadTrailers(id)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.map(trailerResponse -> trailerResponse.getTrailersList().getTrailers())
				.subscribe(
						trailersMutableLiveData::setValue,
						throwable -> Log.d(TAG, throwable.toString())
				);
		compositeDisposable.add(disposable);
	}

	public void loadReviews(int id) {
		Disposable disposable = ApiFactory.apiService.loadReview(id)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.map(ReviewResponse::getReviewList)
				.subscribe(
						reviewsMutableLiveData::postValue,
						throwable -> Log.d(TAG, throwable.toString())
				);
		compositeDisposable.add(disposable);
	}

	public LiveData<Movie> getFavoriteMovie(int movieId) {
		return movieDAO.getFavoriteMovie(movieId);
	}

	public void addMovie(Movie movie) {
		Disposable disposable = movieDAO.insertMovie(movie)
				.subscribeOn(Schedulers.io())
				.subscribe();
		compositeDisposable.add(disposable);
	}

	public void deleteMovie(int movieId) {
		Disposable disposable = movieDAO.deleteMovie(movieId)
				.subscribeOn(Schedulers.io())
				.subscribe();
		compositeDisposable.add(disposable);
	}

	@Override
	protected void onCleared() {
		super.onCleared();
		compositeDisposable.dispose();
	}

	public MovieDetailViewModel(@NonNull Application application) {
		super(application);
		movieDAO = MovieDatabase.getInstance(application).movieDAO();
	}
}
