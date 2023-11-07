package com.example.moviesapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviesapp.R;
import com.example.moviesapp.pojo.Trailer;

import java.util.ArrayList;
import java.util.List;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.TrailersViewHolder> {

    private List<Trailer> trailers = new ArrayList<>();
    private OnTrailerClickListener onTrailerClickListener;

    public void setTrailers(List<Trailer> trailers) {
        this.trailers = trailers;
        notifyDataSetChanged();
    }

    public void setOnTrailerClickListener(OnTrailerClickListener onTrailerClickListener) {
        this.onTrailerClickListener = onTrailerClickListener;
    }

    @NonNull
    @Override
    public TrailersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(
                        R.layout.trailer_item,
                        parent,
                        false
                );
        return new TrailersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailersViewHolder holder, int position) {
        Trailer trailer = trailers.get(position);
        holder.tvTrailerName.setText(trailer.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onTrailerClickListener != null) {
                    onTrailerClickListener.onTrailerClick(trailer);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return trailers.size();
    }

    static class TrailersViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvTrailerName;

        public TrailersViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTrailerName = itemView.findViewById(R.id.tvTrailerName);
        }
    }

    public interface OnTrailerClickListener {
        void onTrailerClick(Trailer trailer);
    }
}
