package com.app.services;

import java.util.List;

import com.app.model.MovieModel;

public interface UserServices {
	
	public List<MovieModel> getTrendingMovies();
	
	public boolean isMovieAddInHistory(String username, String movieName);

}
