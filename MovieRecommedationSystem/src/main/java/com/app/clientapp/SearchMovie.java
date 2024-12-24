package com.app.clientapp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.app.model.MovieModel;
import com.app.services.*;

public class SearchMovie {

	static void displayMovieHelper(List<MovieModel> movieData)
	{
		Set<String> genre = new HashSet<>();
		Set<String> language = new HashSet<>();

		if (!movieData.isEmpty()) {
			for (MovieModel m : movieData) {
				System.out.println("Name: \t\t" + m.getMovieName() + "\nDirector: \t" + m.getDirector() + "\nActor: \t\t"
						+ m.getActor() + "\nActress: \t" + m.getActress() + "\nGenres: \t");
				genre = m.getGenres();
				for (String str : genre) {
					System.out.print("\t\t"+str + "\n");
				}
				System.out.println("Duration: \t" + m.getDuration()+"\nLanguage: \t");
				
				language = m.getLanguage();
				for (String str : language) {
					System.out.print("\t\t"+str + "\n");
				}
				
				System.out.println("Year: \t\t" + m.getYear()+"\nIMDb Rating: \t"+m.getRating()+"\n"+"Discription :\n\t"+m.getDescription());
				System.out.println("\n=======================================================\n");
			}
		} else
			System.out.println("\n===== !!! No Movie Available !!! =====\n");
	}
	static void movieSearchOperation() {
		String searchChoice;
		Scanner sc = ScannerClass.getScanner();
		MovieModel model ;
		AdminServices adminService = new AdminServicesImpl();
		do {

			System.out.println(
					"1:Search Movie by Name\n2:Search Movie by Genre\n3:Search Movie by Year\n4:Search Movie by Director\n"
					+ "5:Search Movie by Actor\n6:Search Movie by Actress\n7:Exit\n8:Logout\n\nEnter your choice");
			searchChoice = sc.nextLine();
			String regex = "[A-Za-z 0-9-]+";

			switch (searchChoice) {
			case "1":
				System.out.println("\nEnter movie name ");
				String movieName = sc.nextLine();
				if(movieName.matches(regex))
				{
					List<MovieModel> movieData = new ArrayList<>(adminService.getMoviesBySearch(movieName,"movieName"));
					displayMovieHelper(movieData);
				}
				else {
					System.out.println("\n===== !!! Invalid Movie Name !!! =====\n");
				}
				break;

			case "2":
				System.out.println("\nEnter movie genre ");
				String genre = sc.nextLine();
				if(genre.matches(regex))
				{
					List<MovieModel> movieData = new ArrayList<>(adminService.getMoviesBySearch(genre,"movieGenre"));
					displayMovieHelper(movieData);
				}
				else {
					System.out.println("\n===== !!! Invalid Movie Genre Name !!! =====\n");
				}
				break;

			case "3":
				System.out.println("\nEnter movie year ");
				String movieYear = sc.nextLine();
				if(movieYear.matches("[0-9]{4}"))
				{
					List<MovieModel> movieData = new ArrayList<>(adminService.getMoviesBySearch(movieYear,"movieYear"));
					displayMovieHelper(movieData);
				}
				else {
					System.out.println("\n===== !!! Invalid Movie Year !!! =====\n");
				}
				break;

			case "4":
				System.out.println("\nEnter movie director ");
				String director = sc.nextLine();
				if(director.matches(regex))
				{
					List<MovieModel> movieData = new ArrayList<>(adminService.getMoviesBySearch(director,"director"));
					displayMovieHelper(movieData);
				}
				else {
					System.out.println("\n===== !!! Invalid Movie Director Name !!! =====\n");
				}
				break;

			case "5":
				System.out.println("\nEnter movie actor ");
				String actor = sc.nextLine();
				if(actor.matches(regex))
				{
					List<MovieModel> movieData = new ArrayList<>(adminService.getMoviesBySearch(actor,"actor"));
					displayMovieHelper(movieData);
				}
				else {
					System.out.println("\n===== !!! Invalid Movie Actor Name !!! =====\n");
				}
				break;

			case "6":
				System.out.println("\nEnter movie actress ");
				String actress = sc.nextLine();
				if(actress.matches(regex))
				{
					List<MovieModel> movieData = new ArrayList<>(adminService.getMoviesBySearch(actress,"actress"));
					displayMovieHelper(movieData);
				}
				else {
					System.out.println("\n===== !!! Invalid Movie Actress Name !!! =====\n");
				}
				break;

			case "7":
				System.out.println("Return to movie section");
				break;
				
			case "8":
				MovieRecommendationSystem.callMain();
				break;
				
			default:
				System.out.println("\n===== !!! Invalid Input !!! =====\n");
				break;
			}

		} while (!searchChoice.equals("7"));
	}
}
