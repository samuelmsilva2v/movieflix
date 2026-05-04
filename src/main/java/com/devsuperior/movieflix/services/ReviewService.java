package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private AuthService authService;

    @Transactional
    public ReviewDTO insert(ReviewDTO dto) {
        Review review = new Review();
        review.setText(dto.getText());

        Movie movie = movieRepository.getReferenceById(dto.getMovieId());
        review.setMovie(movie);

        User user = authService.authenticated();
        review.setUser(user);

        review = reviewRepository.save(review);

        ReviewDTO result = new ReviewDTO();
        result.setId(review.getId());
        result.setText(review.getText());
        result.setMovieId(review.getMovie().getId());
        result.setUserId(review.getUser().getId());
        result.setUserName(review.getUser().getName());
        result.setUserEmail(review.getUser().getEmail());

        return result;
    }
}
