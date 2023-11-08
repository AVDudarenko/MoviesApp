package com.example.moviesapp.pojo;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Rating implements Serializable {
	@SerializedName("kp")
	double kp;
	@SerializedName("imdb")
	double imdb;

	public Rating(double ratingKp, double ratingImdb) {
		this.kp = ratingKp;
		this.imdb = ratingImdb;
	}

	public Rating() {
	}

	public double getKp() {
		return kp;
	}

	public double getImdb() {
		return imdb;
	}

	public void setKp(double kp) {
		this.kp = kp;
	}

	public void setImdb(double imdb) {
		this.imdb = imdb;
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
