package com.app.repository;

import java.util.List;
import java.util.Map;

import com.app.model.MovieModel;

public interface UserRepository {

	public List<MovieModel> getTrendingMovies();
	
	public boolean isMovieAddInHistory(String username, String movieName);
	
	public boolean isAddMovieInWatchList(String username, String movieName);
	
	public boolean hasMovieRated(String username, String movieName, String rating);
	
	public Map<List<String>,String> getUserHistory(String username);
	
	public boolean isDeleteHistory(String username, String movieName);
	
	public boolean isDeleteAllHistory(String username);
	
	public List<String> getUserWatchlist(String username);
	
	public boolean isDeleteWatchlist(String username, String movieName);
	
	public boolean isDeleteAllWatchlist(String username);
}
