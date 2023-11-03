package com.example.moviesapp.pojo;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Rating {
	@SerializedName("kp")
	private final double kp;
	@SerializedName("imdb")
	private final double imdb;

	public Rating(double ratingKp, double ratingImdb) {
		this.kp = ratingKp;
		this.imdb = ratingImdb;
	}

	public double getKp() {
		return kp;
	}

	public double getImdb() {
		return imdb;
	}

	@NonNull
	@Override
	public String toString() {
		return "Rating{" +
				"kp='" + kp + '\'' +
				", imdb='" + imdb + '\'' +
				'}';
	}
}
