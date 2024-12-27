package com.app.clientapp;

import com.app.services.*;
import java.util.*;

import com.app.model.MovieModel;

public class Admin {
	
	static void showMovieList()
	{
		AdminServices adminService = new AdminServicesImpl();
		int count = 1;
		List<MovieModel> movieData = new ArrayList<>(adminService.getAllMovies());
		if (!movieData.isEmpty()) {
			System.out.println("\n  ***** All Movies List *****\n");
			for (MovieModel model : movieData) {
				System.out.println("\t" + count++ + ".  " + model.getMovieName());
			}
		}
	}
	public static void AdminFunctinality() {
		Scanner sc = ScannerClass.getScanner();
		MovieModel model;
		AdminServices adminService = new AdminServicesImpl();
		UserServices userService = new UserServicesImpl();
		String choice;
		do {
			System.out.println("1:Movie Section\n2:User Section\n3:Profile\n4:Logout\nEnter your choice");
			choice = sc.nextLine();
			String adminChoice;
			String regex = "[a-zA-Z0-9 .-]+";
			switch (choice) {
			case "1":
				do {
					System.out.println(
							"\n1:Add movie\n2:View all movies\n3:Search movie\n4:Delete movie\n5:Retrive deleted movie\n6:Update movie"
									+ "\n7:Logout\n\nEnter your choice");
					adminChoice = sc.nextLine();

					switch (adminChoice) {
					case "1":
						System.out.println("Enter movie name");
						String movieName = sc.nextLine();
						System.out.println("Enter movie duration");
						String duration = sc.nextLine();
						int count = 0;
						Map<String,Integer> allGenre = new LinkedHashMap<>(userService.getAllGenres());
						System.out.println("\n***** All Genre List *****\n");
						
						for(Map.Entry<String, Integer> map : allGenre.entrySet())
						{
							if(count%5==0)
								System.out.println();
							System.out.print("\t"+ ++count +".  "+map.getKey());
							
						}
						System.out.println("\n\nEnter movie genres  (press 0 to finish) ");
						Set<String> genres = new HashSet<>();
						
						while (true) {
							String genre = sc.nextLine();
							if (genre.equals("0")) {
								break;
							} else {
								if (genre.matches("[a-zA-Z -]+")) {
									genres.add(genre);
								} else {
									System.out.println("\n===== !!! Invalid Genre !!! =====\n");
								}
							}
						}

						System.out.println("Enter movie languages  (press 0 to finish) ");
						Set<String> languages = new HashSet<>();
						while (true) {
							String language = sc.nextLine();
							if (language.equals("0")) {
								break;
							} else {
								if (language.matches("[a-zA-Z]+")) {
									languages.add(language);
								} else {
									System.out.println("\n===== !!! Invalid Language !!! =====\n");
								}
							}
						}

						System.out.println("Enter movie year");
						String year = sc.nextLine();
						System.out.println("Enter IMDb rating");
						String rating = sc.nextLine();
						System.out.println("Enter movie director name");
						String director = sc.nextLine();
						System.out.println("Enter movie actor name");
						String acter = sc.nextLine();
						System.out.println("Enter movie actress name");
						String actress = sc.nextLine();
						System.out.println("Enter movie description of movie");
						String description = sc.nextLine();

						if (movieName.matches(regex) && duration.matches(regex) && year.matches("[0-9]+")) {
							model = new MovieModel(movieName, duration, genres, languages, year, director, acter,
									actress, description, rating);

							System.out.println(adminService.isAddMovie(model)
									? "\n***** " + movieName + " movie is added successfully *****\n"
									: "===== !!! Movie is not added !!! =====");
						} else {
							System.out.println("\n===== !!! Invalid Movie Input !!! =====\n");
						}
						break;

					case "2":
						List<MovieModel> movieData = new ArrayList<>(adminService.getAllMovies());
						Set<String> genre = new HashSet<>();
						Set<String> language = new HashSet<>();

						if (!movieData.isEmpty()) {
							for (MovieModel m : movieData) {
								System.out.println("Name: \t\t" + m.getMovieName() + "\nDirector: \t" + m.getDirector()
										+ "\nActor: \t\t" + m.getActor() + "\nActress: \t" + m.getActress()
										+ "\nGenres: \t");
								genre = m.getGenres();
								for (String str : genre) {
									System.out.print("\t\t" + str + "\n");
								}
								System.out.println("Duration: \t" + m.getDuration() + "\nLanguage: \t");

								language = m.getLanguage();
								for (String str : language) {
									System.out.print("\t\t" + str + "\n");
								}

								System.out.println("Year: \t\t" + m.getYear() + "\nIMDb Rating: \t" + m.getRating()
										+ "\n" + "Discription :\n\t" + m.getDescription());
								System.out.println("\n=======================================================\n");
							}
						} else
							System.out.println("\n===== !!! No Movie Available !!! =====\n");

						break;

					case "3":
						SearchMovie.movieSearchOperation();
						break;

					case "4":
						showMovieList();
						System.out.println("\nEnter movie name ");
						movieName = sc.nextLine();
						String movieYear = "";
						boolean flag = false;
						if (movieName.matches(regex)) {
							movieData = new ArrayList<>(adminService.getMoviesBySearch(movieName, "movieName"));
							SearchMovie.displayMovieHelper(movieData);
							if (!movieData.isEmpty()) {
								if (movieData.size() > 1) {
									System.out.println("\nEnter movie year ");
									movieYear = sc.nextLine();
									if (movieYear.matches("[0-9]{4}")) {
										System.out.println(adminService.isDeleteMovie(movieName, movieYear)
												? "***** " + movieName + " is deleted successfully *****\n"
												: "===== !!! Failed to delete !!! =====\n");
									} else {
										System.out.println("\n===== !!! Invalid Movie Year !!! =====\n");
									}

								} else {
									for (MovieModel mModel : movieData) {
										if (mModel.getMovieName().equalsIgnoreCase(movieName)) {
											movieYear = mModel.getYear();
											flag = true;
											break;
										}
									}
									if (flag) {
										System.out.println(adminService.isDeleteMovie(movieName, movieYear)
												? "***** " + movieName + " movie is deleted successfully *****\n"
												: "===== !!! Failed to delete !!! =====\n");
									} else {
										System.out.println("\n===== !!! Failed  !!! =====\n");
									}
								}
							}

						} else {
							System.out.println("\n===== !!! Invalid Movie Name !!! =====\n");
						}

						break;
						
					case "5":
						List<String> blockedMovieList = new ArrayList<>(adminService.getBlockedMovie());
						if(!blockedMovieList.isEmpty()) {
							System.out.println("\n***** All Blocked Movies *****\n");
							System.out.println("\tID  Movie name\n");
							count = 1;
							for(String movies :blockedMovieList) {
								System.out.println("\t"+count++ +". "+ movies);
							}
							System.out.println("\nEnter movie name to retrive (Press 0 to exit) ");
							movieName = sc.nextLine();
							if(!movieName.equals("0")) {
								System.out.println(adminService.isUnblockMovie(movieName)?"\n***** " + movieName + 
										" movie retrive successfully  *****\n":"\n===== !!! Failed to retrive !!! =====\n");
							}
						}
						else {
							System.out.println("\n===== !!! Movie Blacklist is Empty !!! =====\n");
						}
						
						break;

					case "6":
						UpdateMovie.updateMovie();
						break;

					case "7":
						System.out.println("Logged out...\n");
						break;

					default:
						System.out.println("\n===== !!! Invalid Input !!! =====\n");
						break;
					}

				} while (!adminChoice.equals("7"));
				break;

			case "2":
				UserSectionForAdmin.userSection();
				break;

			case "3":
				User.showProfile();
				break;

			case "4":
				System.out.println("Return to login page......\n");
				break;

			default:
				System.out.println("\n===== !!! Invalid Input !!! =====\n");
				break;
			}
		} while (!choice.equals("4"));
	}
}
