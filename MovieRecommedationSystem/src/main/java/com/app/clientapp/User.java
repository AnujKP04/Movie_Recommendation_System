package com.app.clientapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.app.model.MovieModel;
import com.app.model.UserModel;
import com.app.services.UserServices;
import com.app.services.UserServicesImpl;

public class User {

	static void userFunctionality() {
		Scanner sc = ScannerClass.getScanner();
		UserServices userServices= new UserServicesImpl();
		String userChoice = null ;
		do {
			System.out.println("\n1: Trending Movies\n2: Your Interest\n3: Search Movie\n4: User History\n5: Watchlist\n6: Profile"
					+ "\n7: Exit\nEnter your choice");
			userChoice = sc.nextLine();
			
			switch(userChoice) {
			case "1":
				List<MovieModel> movieData = new ArrayList<>(userServices.getTrendingMovies());
				
				int count = 1;
				System.out.println("\n***** Top 10 Trending Movies *****\n");
				for(MovieModel m : movieData)
				{
					System.out.println(count++ +". " + m.getMovieName());
				}
				
				System.out.println("___________________________________");
						
				String movieName= "";
				boolean flag = false;
				List<MovieModel> selectedMovieData = new ArrayList<>();
				while(true) {
					System.out.println("\nEnter name of movie from trending list: (press 0 for exit)");
					movieName = sc.nextLine();
					
					if(movieName.equals("0"))
						break;
					for(MovieModel m : movieData)
					{
						if(m.getMovieName().equalsIgnoreCase(movieName))
						{
							selectedMovieData.add(m);
							flag = true;
							break;
						}
						
					}
					if(flag) {
						System.out.println("\n=======================================================\nDescription:");
						SearchMovie.displayMovieHelper(selectedMovieData);
						
						MovieOptions.movieOptions(movieName);
						break;
					}
					else {
						
						System.out.println("\n===== !!! Invalid Input !!! =====\n");
					}
				}
				
				
				break;
				
			case "2":
				break;
				
			case "3":
				SearchMovie.movieSearchOperation();
				break;
				
			case "4":
				UserHistory.userHistory();
				break;
				
			case "5":
				Watchlist.watchListFunctinality();
				break;
				
			case "6":
				UserModel profile = userServices.getProfile(MovieRecommendationSystem.getUsername());
				if(profile != null) {
					System.out.println("\n===== USER PROFILE =====\n");
					System.out.println("Username: "+profile.getUsername()+"\n\nName: "+profile.getName()+"\nEmail: "+profile.getEmail()
					+"\nContact: "+profile.getContact()+"\n");
					
					UpdateProfile.editProfile(MovieRecommendationSystem.getUsername());
				}
				else {
					System.out.println("\n===== !!! Something went wrong !!! =====\n");
				}
				break;
			case "7":
				System.out.println("Return to Login Section.....");
				break;
				
			default:
				System.out.println("\n===== !!! Invalid Input !!! =====\n");
				break;
			}
			
		}while(!userChoice.equals("7"));
	}
}
