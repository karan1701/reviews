package com.rtb.reviews.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.rtb.reviews.model.BookReview;

@RestController
@RequestMapping("/review")
public class ReviewController {

	Logger logger = LoggerFactory.getLogger(ReviewController.class);

	@Autowired
	RestTemplate restTemplate;

	Random rd = new Random();

	String[] goodComments = new String[] { "Good", "Awesome book", "Very inspiring" };
	String[] badComments = new String[] { "Bad", "Not a good book", "Slow and boring" };

	@GetMapping("/{bookid}")
	public List<BookReview> getBookReview(@PathVariable(name = "bookid") String bookId) {
		logger.info("Getting book review for {}", bookId);

		List<BookReview> bookReviews = new ArrayList<>();
		BookReview bookReview;
		int i = ReviewController.getRandomNumberInRange(4, 10);
		int review = 0;
		for (int j = 0; j < i; j++) {
			review = ReviewController.getRandomNumberInRange(1, 100);
			logger.info("Getting rating for review id: {}", review);
			String rating = restTemplate.getForObject("http://ratings-app:9094/ratings/" + review, String.class);
			Double bookRating = Double.valueOf(rating);
			String comment = null;
			if (Double.compare(bookRating, Double.valueOf(2.0)) < 0) {
				comment = badComments[ReviewController.getRandomNumberInRange(0, 2)];
			} else {
				comment = goodComments[ReviewController.getRandomNumberInRange(0, 2)];
			}
			bookReview = new BookReview(comment, bookRating);
			bookReviews.add(bookReview);
		}
		return bookReviews;
	}

	private static int getRandomNumberInRange(int min, int max) {
		Random r = new Random();
		return r.ints(min, (max + 1)).limit(1).findFirst().getAsInt();

	}

}
