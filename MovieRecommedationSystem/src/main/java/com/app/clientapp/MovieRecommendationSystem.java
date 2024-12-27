package com.app.clientapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.app.model.UserModel;
import com.app.services.AdminServices;
import com.app.services.AdminServicesImpl;

public class MovieRecommendationSystem {
	static String userName;
	public static String getUsername() {
		
		return userName;
	}

	public static void  callMain() {
		String args[]= {};
		main(args);
	}
	
	public static void main(String[] args) {

		System.out.println("\n\n\n\t\t\t\t\t\t 𝙈𝙤𝙫𝙞𝙚 𝙍𝙚𝙘𝙤𝙢𝙢𝙚𝙣𝙙𝙖𝙩𝙞𝙤𝙣 𝙎𝙮𝙨𝙩𝙚𝙢 \n\n\n");
		UserModel userModel;
		AdminServices adminService = new AdminServicesImpl();
		Scanner sc = ScannerClass.getScanner();
		String choice;
		do {
			System.out.println("1:Login \n2:Register\n3:Exit\nEnter your choice");
			choice = sc.nextLine();

			switch (choice) {
			case "1":
				System.out.println("Enter username");
				String username = sc.nextLine();
				System.out.println("Enter password ");
				String password = sc.nextLine();
				
				List<String> loginInfo = new ArrayList<String>( adminService.getNameByLogin(username, password));
			
				if(!loginInfo.isEmpty())
				{
					if(loginInfo.get(1).equals("Admin")) 
					{
						System.out.println("\n***** Welcome back "+ loginInfo.get(0)+" *****\n");
						userName=username;
						Admin.AdminFunctinality();
					}
					else {
						System.out.println("\n***** Welcome back "+ loginInfo.get(0)+" *****\n");
						userName=username;
						User.userFunctionality();
					}
				}
				else
				{
					System.out.println("\n===== !!! You Have To Register First !!! =====\n");
				}
				break;

			case "2":
				String userChoice;
				String rePassword;
				System.out.println("1:Admin Registration\n2:User Registration\n\nEnter your choice");
				userChoice = sc.nextLine();

				System.out.println("\nEnter your name");
				String name = sc.nextLine();
				System.out.println("\nEnter your email");
				String email = sc.nextLine();
				while((adminService.getUserEmail()).contains(email))
				{
					System.out.println("Email already registered use another (For exit press 0)");
					email = sc.nextLine();
					if(email.equals("0"))
					{
						System.out.println("!!! Session Terminated !!!");
						
						System.exit(0);
					}
				}
				
				System.out.println("\nEnter your contact");
				String contact = sc.nextLine();
				while((adminService.getUserContact()).contains(contact))
				{
					System.out.println("Contact already registered use another (For exit press 0)");
					contact = sc.nextLine();
					if(contact.equals("0"))
					{
						System.out.println("!!! Session Terminated !!!");
						System.exit(0);
					}
				}
				System.out.println("\nEnter your username");
				username = sc.nextLine();
				while((adminService.getUsername()).contains(username))
				{
					System.out.println("Username is unavailable use another (For exit press 0)");
					username = sc.nextLine();
					if(username.equals("0"))
					{
						System.out.println("!!! Session Terminated !!!");
						System.exit(0);
					}
				}
				System.out.println("\nEnter your password (must between 6-16 letters)");
				password = sc.nextLine();

				if (name.matches("[a-zA-Z ]+") && email.matches("^[a-z0-9.]+@[a-z]+\\.[a-z]{2,3}$")
						&& contact.matches("[0-9]+") && contact.length() == 10
						&& (password.length() > 5 && password.length() < 17)) {
					boolean flag = false;
					while (true) {
						System.out.println("\nRe-enter your password (press 0 to exit)");
						rePassword = sc.nextLine();
						if (password.equals(rePassword)) {
							flag = true;
							break;
						} else if (rePassword.equals("0")) {
							break;
						} else {
							System.out.println("\n===== !!! Wrong Password !!! =====\n");
						}
					}

					if (flag) {

						switch (userChoice) {
						case "1":
							System.out.println("\nEnter security code");
							if (sc.nextLine().equals("1234")) {
								userModel = new UserModel(name,email,contact,username,password,"","Admin");
								System.out.println(adminService.isRegister(userModel)?"Admin Registered Successfully":
									"Admin Registeration Failed");
							} else {
								System.out.println(
										"\n===== !!! Wrong Security Code !!! =====\n Enter again security code");
								if (sc.nextLine().equals("1234")) {

									userModel = new UserModel(name,email,contact,username,password,"","Admin");
									System.out.println(adminService.isRegister(userModel)?"Admin Registered Successfully":
											"Admin Registeration Failed");
								} else {
									System.out.println("\n===== !!! Wrong Security Code !!! =====\n");
								}
							}
							break;

						case "2":
							userModel = new UserModel(name,email,contact,username,password,"","User");
							System.out.println(adminService.isRegister(userModel)?"User Registered Successfully":
									"User Registeration Failed");
							break;

						default:
							System.out.println("\n===== !!! Invalid Choice !!! =====\n");
						}
					}
				} else {
					System.out.println("\n===== !!! Invalid Input !!! =====\n");
				}
				break;

			case "3":
				System.out.println("Exit");
				System.exit(0);

			default:
				System.out.println("\n===== !!! Invalid Input !!! =====\n");
				break;
			}
		} while (!choice.equals("3"));
	}
}
