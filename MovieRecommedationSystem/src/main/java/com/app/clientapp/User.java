package com.app.clientapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.app.model.MovieModel;
import com.app.services.UserServices;
import com.app.services.UserServicesImpl;

public class User {

	static void userFunctionality() {
		Scanner sc = ScannerClass.getScanner();
		UserServices userServices= new UserServicesImpl();
		String userChoice = null ;
		do {
			System.out.println("\n1: Trending Movies\n2: Your Interest\n3:Search Movie\n4: User History\n5: Watchlist\n6: Exit\n"
					+ "Enter your choice");
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
				while(!movieName.equals("0")) {
					System.out.println("\nSelect movie from trending list: (press 0 for exit)");
					movieName = sc.nextLine();
					
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
					}
					else {
						System.out.println("\n===== !!! Invalid Input !!! =====\n");
					}
				}
				
				
				break;
				
			case "2":
				break;
				
			case "3":
				break;
				
			case "4":
				break;
				
			case "5":
				break;
				
			default:
				System.out.println("\n===== !!! Invalid Input !!! =====\n");
				break;
			}
			
		}while(!userChoice.equals("6"));
	}
}
