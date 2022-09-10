package com.rtb.reviews.model;

public class BookReview {

	String comment;
	Double ratings;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Double getRatings() {
		return ratings;
	}

	public void setRatings(Double ratings) {
		this.ratings = ratings;
	}

	@Override
	public String toString() {
		return "ProductReview [comment=" + comment + ", ratings=" + ratings + "]";
	}

	public BookReview(String comment, Double ratings) {
		super();
		this.comment = comment;
		this.ratings = ratings;
	}

}
