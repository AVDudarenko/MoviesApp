package com.example.moviesapp.view.model;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.moviesapp.ApiFactory;
import com.example.moviesapp.pojo.Movie;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainViewModel extends AndroidViewModel {

	private final MutableLiveData<List<Movie>> moviesLiveData = new MutableLiveData<>();
	private final CompositeDisposable compositeDisposable = new CompositeDisposable();
	private int page = 1;

	public MainViewModel(@NonNull Application application) {
		super(application);
	}

	public LiveData<List<Movie>> getMoviesLiveData() {
		return moviesLiveData;
	}

	/**
	 * method for loading movies
	 */
	public void loadMovies() {
		Disposable disposable = ApiFactory.apiService.loadMovies(page)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(
						movieResponse -> {
							page++;
							moviesLiveData.setValue(movieResponse.getMovies());
						},
						throwable -> Log.d("MainViewModel", throwable.toString())
				);
		compositeDisposable.add(disposable);
	}

	@Override
	protected void onCleared() {
		super.onCleared();
		compositeDisposable.dispose();
	}
}
