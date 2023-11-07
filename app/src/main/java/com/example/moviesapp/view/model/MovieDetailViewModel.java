package com.example.moviesapp.view.model;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.moviesapp.ApiFactory;
import com.example.moviesapp.pojo.Trailer;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MovieDetailViewModel extends AndroidViewModel {
    private static final String TAG = "MovieDetailViewModel";
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final MutableLiveData<List<Trailer>> trailersMutableLiveData = new MutableLiveData<>();

    public LiveData<List<Trailer>> getTrailersMutableLiveData() {
        return trailersMutableLiveData;
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

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }

    public MovieDetailViewModel(@NonNull Application application) {
        super(application);
    }
}
