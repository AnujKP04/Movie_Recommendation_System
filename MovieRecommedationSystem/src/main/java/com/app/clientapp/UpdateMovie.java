package com.app.clientapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.app.model.MovieModel;
import com.app.services.AdminServices;
import com.app.services.AdminServicesImpl;

public class UpdateMovie {
	static Scanner sc = ScannerClass.getScanner();
	static boolean flag = false;
	
	static void displayMoviesList() {
		AdminServices adminService = new AdminServicesImpl();
		int count = 1;
		String movieName="";
		List<MovieModel> movieData = new ArrayList<>(adminService.getAllMovies());
		if (!movieData.isEmpty()) {
			System.out.println("\n  ***** All Movies List *****\n");
			for (MovieModel model : movieData) {
				System.out.println("\t" + count++ + ".  " + model.getMovieName());
			}
			flag=true;
		} else {
			flag = false;
			System.out.println("\n===== !!! No Movie Available !!! =====\n");
		}
	}

	
	static void movieUpdateOperationHelper(String movieDataType, String title)
	{
		AdminServices adminService = new AdminServicesImpl();
		displayMoviesList();
		if(flag) {
			System.out.println("\nEnter Movie Name");
			String movieName = sc.nextLine();
			List<MovieModel>movieData = new ArrayList<>(adminService.getMoviesBySearch(movieName, "movieName"));
			SearchMovie.displayMovieHelper(movieData);
			if(!movieData.isEmpty())
			{
				System.out.println("\nEnter New Movie "+ title);
				String newMovieData = sc.nextLine();
				if(adminService.isUpdateMovie(movieName, newMovieData, movieDataType))
				{
					System.out.println("\n***** Movie Updated Successfully *****\n"); 
					movieData = new ArrayList<>(adminService.getMoviesBySearch(movieName, "movieName"));
					SearchMovie.displayMovieHelper(movieData);
				}
				else {
					System.out.println("\n=====!!! Something Went Wrong !!!=====\n");
				}
			}
		}
	}
	static void updateMovie()
	{
		AdminServices adminService = new AdminServicesImpl();
		String movieChoice="";
		do
		{
			System.out.println("\n1:Update Movie Name\n2:Update Movie Year\n3:Update Movie Duration\n4:Update Movie Rating"
					+ "\n5:Update Movie Description\n6:Previous Menu\n7:Logout\nEnter your choice");
			movieChoice = sc.nextLine();
			switch(movieChoice)
			{
			case "1":
				displayMoviesList();
				if(flag) {
					System.out.println("\nEnter Movie Name");
					String movieName = sc.nextLine();
					List<MovieModel>movieData = new ArrayList<>(adminService.getMoviesBySearch(movieName, "movieName"));
					SearchMovie.displayMovieHelper(movieData);
					if(!movieData.isEmpty())
					{
						System.out.println("\nEnter New Movie Name");
						String newMovieName = sc.nextLine();
						if(adminService.isUpdateMovie(movieName, newMovieName, "movieName"))
						{
							System.out.println("\n***** Movie Name Updated Successfully *****\n"); 
							movieData = new ArrayList<>(adminService.getMoviesBySearch(newMovieName, "movieName"));
							SearchMovie.displayMovieHelper(movieData);
						}
						else {
							System.out.println("\n=====!!! Something Went Wrong !!!=====\n");
						}
					}
				}
				break;
						
			case "2":
				movieUpdateOperationHelper("movieYear","Year");
				break;
				
			case "3":
				movieUpdateOperationHelper("movieDuration","Duration");
				break;
				
			case "4":
				movieUpdateOperationHelper("movieRating","IMDb rating");
				break;
				
			case "5":
				movieUpdateOperationHelper("movieDescription","Description");
				break;
				
			case "6":
				break;
				
			case "7":
				MovieRecommendationSystem.callMain();
				break;
			}
		}while(!movieChoice.equals("6"));
	}
}
