package com.app.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.app.model.MovieModel;

public class UserRepositoryImpl extends DBConnection implements UserRepository {

	AdminRepositoryImpl adminRepoImplObj = new AdminRepositoryImpl();

	@Override
	public List<MovieModel> getTrendingMovies() {

		String trendingMovieQuery = "select m.*,y.releaseyear from movies m join  movieyear y on y.yearid = m.yearid "
				+ "where y.yearid = (select max(yearid) from movieyear) and m.moviestatus = 1 order by m.rating desc limit 10";

		List<MovieModel> movieData = adminRepoImplObj.getAllMoviesHelper(trendingMovieQuery);

		return movieData;
	}

	@Override
	public boolean isMovieAddInHistory(String username, String movieName) {
		try {

			pstmt = conn.prepareStatement("insert into userhistory (userid, movieid)"
					+ " select (select userid from user where username = ?), (select movieid from movies where moviename = ?) "
					+ "where not exists (select 1 from userhistory where userid = (select userid from user where username = ?) "
					+ "and movieid = (select movieid from movies where moviename = ?));");

			pstmt.setString(1, username);
			pstmt.setString(2, movieName);
			pstmt.setString(3, username);
			pstmt.setString(4, movieName);
			int result = pstmt.executeUpdate();

			return result == 1 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean isAddMovieInWatchList(String username, String movieName) {
		try {
			pstmt = conn.prepareStatement("insert into watchlist (userid, movieid) select "
					+ "(select userid from user where username = ?), (select movieid from movies where moviename = ?) "
					+ "where not exists (select 1 from watchlist where userid = (select userid from user where username = ?) "
					+ "and movieid = (select movieid from movies where moviename = ?));");

			pstmt.setString(1, username);
			pstmt.setString(2, movieName);
			pstmt.setString(3, username);
			pstmt.setString(4, movieName);

			int result = pstmt.executeUpdate();

			return result == 1 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean hasMovieRated(String username, String movieName, String rating) {
		try {
			pstmt = conn.prepareStatement(" update userhistory set rating = ? where userid = "
					+ "(select userid from user where username = ?) and "
					+ "movieid =(select movieid from movies where moviename = ?);");

			pstmt.setString(1, rating);
			pstmt.setString(2, username);
			pstmt.setString(3, movieName);

			int result = pstmt.executeUpdate();

			return result == 1 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Map<List<String>,String> getUserHistory(String username) {
		Map<List<String>,String> userHistory = new LinkedHashMap<>();
		try {
			pstmt = conn.prepareStatement(" select m.moviename,date(h.watchedtime),h.rating from movies m join userhistory h on "
					+ "h.movieid= m.movieid where h.userid = (select userid from user where username = ?);");
			pstmt.setString(1, username);
			
			
			
			rs = pstmt.executeQuery();
			while(rs.next()) {

				List<String> watchTime = new ArrayList<String>();
				watchTime.add( rs.getString(3));
				watchTime.add( rs.getString(1));
				
				userHistory.put(watchTime,rs.getString(2));
				
			//	System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
			}
			return userHistory;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return userHistory;
		}	
	}

	public boolean isDeleteDataHelper(String query, String username, String movieName)
	{
		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, username);
			pstmt.setString(2, movieName);

			int result = pstmt.executeUpdate();

			return result == 1 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean isDeleteAllDataHelper(String query, String username)
	{
		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, username);
			int result = pstmt.executeUpdate();

			return result >= 1 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean isDeleteHistory(String username, String movieName) {
		String query =  "delete from userhistory where userid = (select userid from user where username = ?) "
				+ "and movieid = (select movieid from movies where moviename = ?);";
		
		return isDeleteDataHelper(query, username, movieName);
	}
	
	@Override
	public boolean isDeleteAllHistory(String username) {
			String query = "delete from userhistory where userid = (select userid from user where username = ?);";

			return this.isDeleteAllDataHelper(query, username);
	}

	@Override
	public List<String> getUserWatchlist(String username) {
		List<String> watchList = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(" select m.moviename from user u join watchlist l on l.userid = u.userid join "
					+ "movies m on l.movieid = m.movieid where u.userid=(select userid from user where username = ?)");
			pstmt.setString(1, username);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				watchList.add( rs.getString(1));
			}
			return watchList;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return watchList;
		}	
	}
	
	@Override
	public boolean isDeleteWatchlist(String username, String movieName) {
		String query = "  delete from watchlist where userid = (select userid from user where username = ?) "
				+ "and movieid = (select movieid from movies where moviename = ?);";
		return isDeleteDataHelper(query, username, movieName);
	}

	@Override
	public boolean isDeleteAllWatchlist(String username) {
		String query = "delete from watchlist where userid = (select userid from user where username = ?);";
		return this.isDeleteAllDataHelper(query, username);
	}

}
