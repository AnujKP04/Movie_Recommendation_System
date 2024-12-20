package com.app.clientapp;

import java.util.Scanner;

import com.app.services.UserServices;
import com.app.services.UserServicesImpl;

public class MovieOptions {

	static void movieOptions(String movieName) {
		Scanner sc = ScannerClass.getScanner();
		UserServices userServices= new UserServicesImpl();
		String movieOptionsChoice= null;
		do {
			
			System.out.println("1: Watch Movie\n2: Add to watchlist\n3: Rate Movie\n4: Exit\n"
					+ " Enter your choice");
			movieOptionsChoice = sc.nextLine();
			
			switch(movieOptionsChoice) {
			
			case "1":
				userServices.isMovieAddInHistory(MovieRecommendationSystem.getUsername(), movieName);
				break;
				
			case "2":
				break;
				
			case "3":
				break;
				
			default:
				System.out.println("\n===== !!! Invalid Input !!! =====\n");
				break;
			}
			
			
		}while(!movieOptionsChoice.equals("4"));
	}
}
