

import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		try {
			Company company = new Company();
			File f= null;
			Scanner sc= null;
			Scanner scan = null;
			
			System.out.println("If you want to quit type  'exit'\n");
			System.out.println("--- Date: " + company.getDay() + "." + company.getMonth() + "." + company.getYear() + " ---");
			while(true) {
				String input = null;
				String[] split = null;
				
				System.out.print(">>>");
				scan = new Scanner(System.in);
				String consoleInput = scan.nextLine();
				String[] splitName = consoleInput.split(";");
				//LOAD
				if(splitName[0].equalsIgnoreCase("load")) {
					f = new File(splitName[1]);
					sc = new Scanner(f);
				}
				//EXIT
				else if(consoleInput.contains("exit")) {
					scan.close();
					break;
				}
				else {
					input = consoleInput;
				}
				
				while((sc != null && sc.hasNextLine()) || input != null) {
					if(splitName[0].equalsIgnoreCase("load")) {
						input = sc.nextLine();
						split = input.split(";");
						input = input.replace("﻿", "");
						System.out.println(">" + input);
					}
					else {
						split = input.split(";");
					}
					
					//ADD OFFICE
					if (split[0].contains("addOffice")) {
						Office o1 = new Office(split[1],split[2],split[3]);
						company.addOffice(o1);
					}
					//LIST OFFICE
					else if(split[0].contains("listOffice")) {
						company.listOffice();
					}
					//ADD EMPLOYEE
					else if(split[0].contains("addEmployee")) {
						Employee e1 = new Employee(split[1],split[2],split[3],split[4], Integer.parseInt(split[5]));
						company.getOfficee()[Integer.parseInt(split[5])-1].addEmployee(e1, Integer.parseInt(split[5]));
					}
					//LIST EMPLOYEE
					else if(split[0].contains("listEmployee")) {
						if(company.getOfficee()[Integer.parseInt(split[1])-1] != null)
							company.getOfficee()[Integer.parseInt(split[1])-1].listEmployee();
						else
							System.out.println("Office " + split[1] + " does not exist");
					}
					//DELETE OFFICE
					else if(split[0].contains("deleteOffice")) {
						company.deleteOffice(Integer.parseInt(split[1])-1);
					}
					//DELETE EMPLOYEE
					else if(split[0].contains("deleteEmployee")) {
						company.getOfficee()[Integer.parseInt(split[1])-1].deleteEmployee(Integer.parseInt(split[2])-1);	
					}
					else if(split[0].contains("addCustomer")){
						Customer customer1 = new Customer(split[1],split[2]);
						company.addCustomer(customer1);
					}
	
					else if(split[0].contains("listCustomer")) {
						company.listCustomer();
					}
					// ADD CAR
					else if(split[0].equals("addCar")) {
						Car c1 = new Car(split[1],split[2],split[3],split[4],Integer.parseInt(split[5]));
						company.getOfficee()[Integer.parseInt(split[5])-1].addCar(c1);;
					}
					// LIST CAR
					else if (split[0].equals("listCar")) {
						if (company.getOfficee()[Integer.parseInt(split[1]) - 1] != null)
							company.getOfficee()[Integer.parseInt(split[1]) - 1].listCar();
						else
							System.out.println();
					}
					else if(split[0].contains("addCarRequestNRandom")) {
	                    company.addCarRequestNRandom(Integer.parseInt(split[1]),Integer.parseInt(split[2]));
	                }
	                else if(split[0].contains("addCarRequestRandom")) {
	                    company.addCarRequestRandom(Integer.parseInt(split[1]),split[2]);
	                }
					else if (split[0].contains("addCarRequest")) {
						
						if(split.length == 8) {
							
							String[] startDateArr = split[6].split("\\.");
							Date startDate = new Date(startDateArr[0], startDateArr[1], startDateArr[2]);
							
							String[] endDateArr = split[7].split("\\.");
							Date endDate = new Date(endDateArr[0], endDateArr[1], endDateArr[2]);
							
							if(company.dateControl(startDate, endDate)) {
								CarRequest carRequest = new CarRequest(Integer.parseInt(split[1]), Integer.parseInt(split[2]), split[3], split[4], split[5], startDate, endDate);
								
								if (company.getOfficee()[Integer.parseInt(split[1]) - 1] != null)
									company.addCarRequest(carRequest, Integer.parseInt(split[1]) - 1);
								else
									System.out.println("addCarRequest - Office Error");
							}
							else 
								System.out.println("Invalid Date!!!");
						}
						else if(split.length == 7) {
							
							Random random = new Random();
							int randomOffice = random.nextInt(company.getCounter());
							
							String[] startDateArr = split[5].split("\\.");
							Date startDate = new Date(startDateArr[0], startDateArr[1], startDateArr[2]);
							
							String[] endDateArr = split[6].split("\\.");
							Date endDate = new Date(endDateArr[0], endDateArr[1], endDateArr[2]);
							
							if(company.dateControl(startDate, endDate)) {
								CarRequest carRequest = new CarRequest(randomOffice, Integer.parseInt(split[1]), split[2], split[3], split[4], startDate, endDate);
								
								if (company.getOfficee()[randomOffice] != null)
									company.addCarRequest(carRequest, randomOffice);
								else
									System.out.println("addCarRequest - Office Error");
							}
							else 
								System.out.println("Invalid Date!!!");
						}
					}
					else if(split[0].contains("listCarRequest")) {
						company.listCarRequests();
					}
					else if(split[0].contains("listContract")) {
	                    company.listContracts();
	                }
					
					else if(split[0].contains("nextday")) {
						company.nextDay();
					}
					else
						System.out.println("Wrong enter!!!");
					
					if(sc != null && !sc.hasNextLine()) {
						break;
					}
					input = null;
				}
				System.out.println("-------------------------");				
			}
		}
		catch(Exception e) {
			System.out.println("Something Wrong!!!");
		}
	}

}
