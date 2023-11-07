package com.example.moviesapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviesapp.R;
import com.example.moviesapp.pojo.Review;

import java.util.ArrayList;
import java.util.List;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ReviewsViewHolder> {

	private static final String TYPE_POSITIVE = "Позитивный";
	private static final String TYPE_NEUTRAL = "Нейтральный";

	private List<Review> reviews = new ArrayList<>();

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
		notifyDataSetChanged();
	}

	@NonNull
	@Override
	public ReviewsAdapter.ReviewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext())
				.inflate(
						R.layout.review_item,
						parent,
						false
				);

		return new ReviewsViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull ReviewsAdapter.ReviewsViewHolder holder, int position) {
		Review review = reviews.get(position);
		holder.tvAuthor.setText(review.getAuthor());
		holder.tvReview.setText(review.getReview());

		String type = review.getType();
		int colorResId = android.R.color.holo_red_light;
		switch (type) {
			case TYPE_POSITIVE:
				colorResId = android.R.color.holo_green_light;
				break;
			case TYPE_NEUTRAL:
				colorResId = android.R.color.holo_orange_light;
				break;
		}
		int color = ContextCompat.getColor(holder.itemView.getContext(), colorResId);
		holder.llContainerReview.setBackgroundColor(color);
	}

	@Override
	public int getItemCount() {
		return reviews.size();
	}

	static class ReviewsViewHolder extends RecyclerView.ViewHolder {
		private final LinearLayout llContainerReview;
		private final TextView tvAuthor;
		private final TextView tvReview;

		public ReviewsViewHolder(@NonNull View itemView) {
			super(itemView);
			llContainerReview = itemView.findViewById(R.id.llContainerReview);
			tvAuthor = itemView.findViewById(R.id.tvAuthor);
			tvReview = itemView.findViewById(R.id.tvReview);

		}
	}
}
