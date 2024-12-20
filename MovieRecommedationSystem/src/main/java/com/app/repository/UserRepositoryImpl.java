package com.app.repository;

import java.sql.SQLException;
import java.util.List;

import com.app.model.MovieModel;

public class UserRepositoryImpl extends DBConnection implements UserRepository {

	AdminRepositoryImpl adminRepoImplObj = new AdminRepositoryImpl();
	@Override
	public List<MovieModel> getTrendingMovies() {
		
			String trendingMovieQuery = "select m.*,y.releaseyear from movies m join  movieyear y on y.yearid = m.yearid "
					+ "where y.yearid = (select max(yearid) from movieyear) order by m.rating desc limit 10";
			
			List<MovieModel> movieData = adminRepoImplObj.getAllMoviesHelper(trendingMovieQuery);
			
			return movieData;
	}
	@Override
	public boolean isMovieAddInHistory(String username, String movieName) {
		// TODO Auto-generated method stub
		try {
			
			pstmt = conn.prepareStatement("insert into userhistory(userid,movieid) values((select userid from user where "
					+ "username = ?), (select movieid from movies where moviename = ?));");
			
			pstmt.setString(1, username);
			pstmt.setString(2, movieName);
			
			int result = pstmt.executeUpdate();
			
			return result == 1?true:false;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
}
