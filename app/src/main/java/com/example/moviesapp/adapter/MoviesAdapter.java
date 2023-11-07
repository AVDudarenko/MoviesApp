package com.example.moviesapp.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviesapp.R;
import com.example.moviesapp.pojo.Movie;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private List<Movie> movies = new ArrayList<>();
    private OnReachEndListener onReachEndListener;
    private OnMovieClickListener onMovieClickListener;

    public void setOnMovieClickListener(OnMovieClickListener onMovieClickListener) {
        this.onMovieClickListener = onMovieClickListener;
    }

    public void setOnReachEndListener(OnReachEndListener onReachEndListener) {
        this.onReachEndListener = onReachEndListener;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.movie_item,
                parent,
                false
        );
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);
        Glide.with(holder.itemView)
                .load(movie.getPoster().getUrl())
                .into(holder.ivPoster);

        double ratingKp = movie.getRating().getKp();
        double ratingImdb = movie.getRating().getImdb();

        Context context = holder.itemView.getContext();

        holder.tvRatingKp.setBackground(chooseBackground(context, ratingKp));
        holder.tvRatingImdb.setBackground(chooseBackground(context, ratingImdb));

        holder.tvRatingKp.setText(String.valueOf(ratingKp).substring(0, 3));
        holder.tvRatingImdb.setText(String.valueOf(ratingImdb).substring(0, 3));

        if (position == movies.size() - 5 && onReachEndListener != null) {
            onReachEndListener.onReachEnd();
        }

        holder.itemView.setOnClickListener(view -> {
            if (onMovieClickListener != null) {
                onMovieClickListener.onMovieClick(movie);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {

        private final ImageView ivPoster;
        private final TextView tvRatingKp;
        private final TextView tvRatingImdb;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPoster = itemView.findViewById(R.id.ivPoster);
            tvRatingKp = itemView.findViewById(R.id.tvRatingKp);
            tvRatingImdb = itemView.findViewById(R.id.tvRatingImdb);
        }
    }

    private Drawable chooseBackground(Context context, double rating) {
        int backgroundId;
        if (rating > 7) {
            backgroundId = R.drawable.circle_green;
        } else if (rating > 5) {
            backgroundId = R.drawable.circle_orange;
        } else {
            backgroundId = R.drawable.circle_red;
        }

        return ContextCompat.getDrawable(context, backgroundId);
    }

    public interface OnReachEndListener {
        void onReachEnd();
    }

    public interface OnMovieClickListener {
        void onMovieClick(Movie movie);
    }
}
