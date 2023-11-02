package com.example.moviesapp.pojo;

import com.google.gson.annotations.SerializedName;

public class Rating {
	@SerializedName("kp")
	private String kp;
	@SerializedName("imdb")
	private String imdb;

	public Rating(String ratingKp, String ratingImdb) {
		this.kp = ratingKp;
		this.imdb = ratingImdb;
	}

	public String getKp() {
		return kp;
	}

	public String getImdb() {
		return imdb;
	}

	@Override
	public String toString() {
		return "Rating{" +
				"kp='" + kp + '\'' +
				", imdb='" + imdb + '\'' +
				'}';
	}
}
