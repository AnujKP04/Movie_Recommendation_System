package com.app.services;
import com.app.repository.*;

import java.util.List;

import com.app.model.MovieModel;
import com.app.model.UserModel;

public class AdminServicesImpl implements AdminServices {
	AdminRepository adminRepo = new AdminRepositoryImpl();
	
	@Override
	public boolean isRegister(UserModel userModel) {
		// TODO Auto-generated method stub
		return adminRepo.isRegister(userModel);
	}
	@Override
	public List<String>getNameByLogin(String username, String password) {
		// TODO Auto-generated method stub
		return adminRepo.getNameByLogin(username, password);
	}
	@Override
	public List<String> getUserEmail() {
		// TODO Auto-generated method stub
		return adminRepo.getUserEmail();
	}
	@Override
	public List<String> getUserContact() {
		// TODO Auto-generated method stub
		return adminRepo.getUserContact();
	}
	@Override
	public List<String> getUsername() {
		// TODO Auto-generated method stub
		return adminRepo.getUsername();
	}
	@Override
	public boolean isAddMovie(MovieModel model) {
		return adminRepo.isAddMovie(model);
	}
	@Override
	public List<MovieModel> getAllMovies() {
		// TODO Auto-generated method stub
		return adminRepo.getAllMovies();
	}
//	@Override
//	public List<MovieModel> getMoviesByName(String movieName) {
//		// TODO Auto-generated method stub
//		return adminRepo.getMoviesByName(movieName);
//	}
//	@Override
//	public List<MovieModel> getMoviesByYear(String movieYear) {
//		// TODO Auto-generated method stub
//		return adminRepo.getMoviesByYear(movieYear);
//	}
//	@Override
//	public List<MovieModel> getMoviesByGenre(String movieGenre) {
//		// TODO Auto-generated method stub
//		return adminRepo.getMoviesByGenre(movieGenre);
//	}
//	@Override
//	public List<MovieModel> getMoviesByDirector(String director) {
//		// TODO Auto-generated method stub
//		return adminRepo.getMoviesByDirector(director);
//	}
//	@Override
//	public List<MovieModel> getMoviesByActor(String actor) {
//		// TODO Auto-generated method stub
//		return adminRepo.getMoviesByActor(actor);
//	}
//	@Override
//	public List<MovieModel> getMoviesByActress(String actress) {
//		// TODO Auto-generated method stub
//		return adminRepo.getMoviesByActress(actress);
//	}
	@Override
	public List<MovieModel> getMoviesBySearch(String searchData, String searchType) {
		// TODO Auto-generated method stub
		return adminRepo.getMoviesBySearch(searchData, searchType);
	}
	@Override
	public boolean isDeleteMovie(String movieName, String movieYear) {
		// TODO Auto-generated method stub
		return adminRepo.isDeleteMovie(movieName, movieYear);
	}
	
}
