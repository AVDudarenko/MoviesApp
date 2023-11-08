package com.example.moviesapp.db;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.moviesapp.pojo.Movie;

@Database(entities = {Movie.class}, version = 1, exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {

	private static final String DATABASE_NAME = "favorite.movie";
	private static MovieDatabase instance = null;

	public static MovieDatabase getInstance(Application application) {
		if (instance == null) {
			instance = Room.databaseBuilder(
					application,
					MovieDatabase.class,
					DATABASE_NAME
			).build();
		}
		return instance;
	}

	public abstract MovieDAO movieDAO();

}
