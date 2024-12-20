package com.app.services;

import java.util.List;

import com.app.model.MovieModel;
import com.app.repository.UserRepository;
import com.app.repository.UserRepositoryImpl;

public class UserServicesImpl implements UserServices {

	UserRepository userRepo = new UserRepositoryImpl();
	@Override
	public List<MovieModel> getTrendingMovies() {
		// TODO Auto-generated method stub
		return userRepo.getTrendingMovies();
	}
	@Override
	public boolean isMovieAddInHistory(String username, String movieName) {
		// TODO Auto-generated method stub
		return userRepo.isMovieAddInHistory(username, movieName);
	}

}
