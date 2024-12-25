package com.app.clientapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.app.model.UserModel;
import com.app.services.AdminServices;
import com.app.services.AdminServicesImpl;

public class UserSectionForAdmin {

	static void getUserDataHelper(List<UserModel> userList)
	{
		List<UserModel> userData = new ArrayList<>(userList);
		System.out.println("ID  Name\tContact\t\tDate & Time\t\tEmail\t\t\tUsername\n________________________________"
				+ "_____________________________________________________________________");
		int count = 1;
		
		for(UserModel model : userData) {
			System.out.println(count++ +"  "+model.getName()+"\t"+model.getContact()+"\t"+model.getDate()+
					"\t"+model.getEmail()+"\t\t"+model.getUsername());
		}
	}
	
	static void userSection()
	{
		Scanner sc = ScannerClass.getScanner();
		AdminServices adminService = new AdminServicesImpl();
		String choice="";
		
		do {
			System.out.println("\n***** All Users *****\n");
			List<UserModel> userData = new ArrayList<>(adminService.getAllUserData());
			getUserDataHelper(userData);
			System.out.println("\n1:Block User\n2:Unblock User\n3:Exit\n4:Logout");
			choice = sc.nextLine();
			
			switch(choice)
			{
			case "1":
				System.out.println("Enter username from user list");
				String username = sc.nextLine();
				System.out.println(adminService.isBlockedUser(username)?"\n*** User Blocked Successfully ***\n":
					"\n===== !!! Something went wrong !!! =====\n");
				break;
				
			case "2":
				System.out.println("\n***** All Blocked User *****\n");
				getUserDataHelper(adminService.getAllBlockedUser());
				System.out.println("Enter username");
				username = sc.nextLine(); 
				System.out.println(adminService.isUnblockUser(username)?"\n*** User Unblocked Successfully ***\n":
					"\n===== !!! Something went wrong !!! =====\n"); 
				break;
				
			case "3":
				break;
				
			case "4":
				MovieRecommendationSystem.callMain();
				break;
				
			default :
				System.out.println("\n===== !!! Invalid Input !!! =====\n");
				break;
			}
			
		}while(!choice.equals("3"));
	}
}
