package com.app.repository;

import java.util.List;

import com.app.model.MovieModel;

public interface UserRepository {

	public List<MovieModel> getTrendingMovies();
	
	public boolean isMovieAddInHistory(String username, String movieName);
}
