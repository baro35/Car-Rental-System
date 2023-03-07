import java.util.Random;


public class Company {
	private Office[] offices;;
	private int counter;
	private Customer[] customers;
	private int customer_counter;
	private int Day;
	private int Month;
	private int Year;
	private CarRequest[] carRequests;
	private int carRequestCounter; 
	private Contract[] contracts;
	private int contractCounter;
	
	
	//CONSTRUCTORS
	public Company() {
		offices = new Office[1];
		counter = 0;
		customers = new Customer[1];
		customer_counter = 0;
	    Day = 1;
	    Month = 1;
	    Year = 2021;
	    carRequests = new CarRequest[20];
	    contracts = new Contract[20];
	    contractCounter = 0;
	}
	public Office[] getOfficee() {
		return offices;
	}
	public int getCounter() {
		return counter;
	}	
	public int getDay() {
		return Day;
	}
	public int getMonth() {
        return Month;
    }
	public int getYear() {
	    return Year;
    }
	///////////////////////////////////////////////////METHODS//////////////////////////////////////////////////////
	public void addCarRequestNRandom(int min, int max) {
		if (min<0 || max>7) {
			System.out.println("The maximum range of random request number is 1 and 7");
		}
		else {
			Random rnd = new Random();
			Date startDate = new Date(String.valueOf(Day),"1","2021");
			int day = rnd.nextInt(4);
			day += Integer.parseInt(startDate.getDay());
			int month = 1;
			int year = 2021;
			int number = rnd.nextInt(max-min + 1);
			if (day > 31) {
				month++;
				day -=31;
			}
			if (month > 12) {
				year++;
				month-=12;
			}
			Date endDate = new Date(String.valueOf(day),String.valueOf(month),String.valueOf(year));
			int carcount = 0;
			
			for (int j = 0; j <number + min; j++) {
				int officeID = rnd.nextInt(counter);
				int carcount1;
                while(true) {
                    carcount1 = 0;
                    for (int i = 0; i <offices[officeID].getCarCounter(); i++) {
                        if(offices[officeID].getCars()[i].isRequested){
                            carcount++;
                        }
                    }
                    if (carcount == contractCounter) {
                        System.out.println("  There is not any avaliable car");
                        break;
                    }
                     else if(offices[officeID].getCarCounter() == carcount1) {
                        officeID++;
                    }
                    else break;
                }
				int customerID = rnd.nextInt(customer_counter);
				for (int i = 0; i < offices[officeID].getCarCounter(); i++) {
					if(!(offices[officeID].getCars()[i].isRequested)) {
						CarRequest carRequest = new CarRequest(officeID, customerID + 1, offices[officeID].getCars()[i].getBrand(), offices[officeID].getCars()[i].getModel(), offices[officeID].getCars()[i].getClass1(), startDate, endDate);
						addCarRequest(carRequest, officeID);
						break;
					}
				}
				if(carcount == contractCounter) {
					System.out.println("There is not any avaliable car");
					break;
				}
			}
		}
	}
	public void addCarRequestRandom(int officeID, String classs) {
		Random rnd =new Random(); 
		int day = rnd.nextInt(4);
		Date startDate = new Date("1","1","2021");
		day += Integer.parseInt(startDate.getDay());
		int month = 1;
		int year = 2021;
		if (day > 31) {
			month++;
			day -=31;
		}
		if (month > 12) {
			year++;
			month-=12;
		}
		Date endDate = new Date(String.valueOf(day),String.valueOf(month),String.valueOf(year));	
		int customerID = rnd.nextInt(customer_counter);
		int carcount = 0;
		
		if(officeID == -1) {
			officeID = rnd.nextInt(counter);
			int carcount1;
            while(true) {
                carcount1 = 0;
                for (int i = 0; i <offices[officeID].getCarCounter(); i++) {
                    if(offices[officeID].getCars()[i].isRequested){
                        carcount++;
                    }
                }
                if (carcount == contractCounter) {
                    System.out.println("  There is not any avaliable car");
                    break;
                }
                 else if(offices[officeID].getCarCounter() == carcount1) {
                    officeID++;
                }
                else break;
            }
			for (int i = 0; i < offices[officeID].getCarCounter(); i++) {
				if(!offices[officeID].getCars()[i].isRequested) {
					CarRequest carRequest = new CarRequest(officeID, customerID + 1, offices[officeID].getCars()[i].getBrand(), offices[officeID].getCars()[i].getModel(), classs, startDate, endDate);
					addCarRequest(carRequest, officeID);
					break;
				}
			}
		}
	}
	public void addCarRequest(CarRequest carRequest, int OfficeID) {
			
			boolean isAdded = false;
			boolean isToday = false;
			int employeeContract = 0;
			boolean isEmployeeContract = false;
			boolean fourDay = false;
			int car = -1;
			int randomEmployee = -1;
			int carAvaliableClass = 0;
			int carAvaliableBrand = 0;
			boolean isCarAvaliable = false;
			boolean isThereACar = false;
			int classCounter = 0;
			int brandCounter = 0;
			
		
			// is Today
			if(carRequest.getStartDate().getDay().equals(String.valueOf(getDay()))) isToday = true;
			
			// is Employee Available
			for (int i = 0; i < offices[OfficeID].getEmployeeCounter(); i++) {
				if(offices[OfficeID].getEmployees()[i].isContract()) employeeContract++;
				else {
					offices[OfficeID].getEmployees()[i].setContract(true);
					randomEmployee = i;
					break;
				}
			}
			if(!(employeeContract == offices[OfficeID].getEmployeeCounter())) isEmployeeContract = true;
			
			// is Contract Date is for 4 day
			if(Integer.parseInt(carRequest.getEndDate().getDay()) - Integer.parseInt(carRequest.getStartDate().getDay()) <= 4 && 0 <= Integer.parseInt(carRequest.getEndDate().getDay()) - Integer.parseInt(carRequest.getStartDate().getDay())) {
				fourDay = true;
			}
			
			// is Car Available & is There a Car
			for (int i = 0; i < offices[OfficeID].getCarCounter(); i++) {
				if(!(carRequest.getClass1().equals("*")) && offices[OfficeID].getCars()[i].getClass1().equals(carRequest.getClass1())) classCounter++;
				if(!(carRequest.getClass1().equals("*")) && offices[OfficeID].getCars()[i].getClass1().equals(carRequest.getClass1()) && !offices[OfficeID].getCars()[i].isRequested) carAvaliableClass++;
				if(!(carRequest.getBrand().equals("*")) && offices[OfficeID].getCars()[i].getBrand().equals(carRequest.getBrand())) brandCounter++;
				if(!(carRequest.getBrand().equals("*")) && offices[OfficeID].getCars()[i].getBrand().equals(carRequest.getBrand()) && !offices[OfficeID].getCars()[i].isRequested) carAvaliableBrand++;
			}
			
			
			if(((!(carRequest.getClass1().equals("*"))) && carAvaliableClass != 0) || ((!(carRequest.getBrand().equals("*"))) && carAvaliableBrand != 0))  isCarAvaliable = true;
			if((!(carRequest.getClass1().equals("*")) && classCounter != 0) || ((!(carRequest.getBrand().equals("*")) && brandCounter != 0)))  isThereACar = true;
				
			
	
			boolean check = isToday && isEmployeeContract && fourDay && isCarAvaliable && isThereACar;
			
			
			for(int i = 0; i < offices[OfficeID].getCarCounter(); i++) {
				if(!(carRequest.getClass1().equals("*")) && (offices[OfficeID].getCars()[i].getClass1().equals(carRequest.getClass1())) && (!offices[OfficeID].getCars()[i].isRequested) && check) {
					car = i;
					isAdded = true;
					break;
				}
				else if(!(carRequest.getBrand().equals("*")) && (offices[OfficeID].getCars()[i].getBrand().equals(carRequest.getBrand())) && (!offices[OfficeID].getCars()[i].isRequested) && check) {
					isAdded = true;
					car = i;
					break;
				}
				else if(!(carRequest.getClass1().equals("*")) && (!(carRequest.getBrand().equals("*"))) && (!(carRequest.getModel().equals("*"))) && (!offices[OfficeID].getCars()[i].isRequested) && check) {
					car = i;
					isAdded = true;
					break;
				}
			}
			
			if(isAdded) {
				carRequest.setContract("+");
				offices[OfficeID].getCars()[car].setRequested(true);
				Contract contract = new Contract(contractCounter, randomEmployee, carRequest.getCustomerID(), offices[OfficeID].getCars()[car].getiD(), carRequest.getStartDate(), carRequest.getEndDate());
				
				contracts[contractCounter] = contract;
				contracts[contractCounter].setOfficeID(OfficeID+1);
				offices[OfficeID].setSignContract(offices[OfficeID].getSignContract()+1);
				contractCounter++;
				System.out.println("  " + contractCounter + ".Contract:" + randomEmployee + ";" + carRequest.getCustomerID() + ";" + offices[OfficeID].getCars()[car].getiD() 
						+ ";" + carRequest.getStartDate().listDate() + ";" + carRequest.getEndDate().listDate());
			}
			else {
				
				if(!isToday)System.out.println("  Error: Car request must be for today");
				if(!isEmployeeContract)System.out.println("  Error: No employee for the contract");
				if(!fourDay)System.out.println("  Error: Car requests must be for 1-4 days");
				if(!isCarAvaliable && isThereACar)System.out.println("  Error: Car not available");
				if(!isThereACar)System.out.println("  Error: No car");
				carRequest.setContract("-");
			}		
			carRequest.setID(carRequestCounter);
			carRequests[carRequestCounter] = carRequest;
			carRequestCounter++;
		}


	
	public void listCarRequests() {
		
		for (int i = 0; i < carRequestCounter; i++) {
			carRequests[i].listCarRequest();
		}
	}

	public void listContracts() {

        for (int i = 0; i < contractCounter; i++) {
        	if(contracts[i] != null)
        		contracts[i].listContract();
        }
    }
	
	//Adding Office
	public  void addOffice(Office office) {
		offices[counter] = office;
		offices[counter].setID(counter+1);
		counter++;
		Office[] newofc = new Office[offices.length+1];
		for (int i = 0; i < offices.length; i++) {
			newofc[i] = offices[i];
		}
		offices = newofc;
	}
	
	//Listing Offices
	public  void listOffice() {
		for (int i = 0; i < offices.length; i++) {
			if(offices[i] != null) 
				System.out.println("  " + offices[i].getID()+".Office;"+offices[i].getPhoneNumber()+";"+ offices[i].getAddress()+";"+ offices[i].getCity());
		}
	}
	
	//Delete Office
	public void deleteOffice(int officeID) {
		if(offices[officeID] == null)
			System.out.println("Office " + (officeID+1) + " does not exist!");
		else {
			offices[officeID] = null;
		}
	}
	//AddCustomer
		public void addCustomer(Customer customer){
			customers[customer_counter] = customer;
			customers[customer_counter].setID(customer_counter+1);
			customer_counter++;
			Customer[] temp_customer = new Customer[customers.length+1];
			for (int i = 0; i < customers.length; i++) {
				temp_customer[i] = customers[i];
			}
			customers = temp_customer;
		}
		
		//ListCustomer
		public void listCustomer() {
			for (int i = 0; i < customers.length; i++) {
				if(customers[i] != null)
					System.out.println("  " + customers[i].getID() + ".Customer;" + customers[i].getName() + ";" + customers[i].getSurname());
			}
		}
		
		//DATE CONTROL
		public boolean dateControl(Date startDate, Date endDate) {
			boolean flag = true;
			
			if(Integer.parseInt(startDate.getYear().replace(" ", "")) > Integer.parseInt(endDate.getYear().replace(" ", ""))) {
				flag = false;
			}
			else if(Integer.parseInt(startDate.getYear().replace(" ", "")) == Integer.parseInt(endDate.getYear().replace(" ", ""))) {
				if(Integer.parseInt(startDate.getMonth().replace(" ", "")) > Integer.parseInt(endDate.getMonth().replace(" ", ""))) {
					flag = false;
				}
				else if(Integer.parseInt(startDate.getMonth().replace(" ", "")) == Integer.parseInt(endDate.getMonth().replace(" ", ""))) {
					if(Integer.parseInt(startDate.getDay().replace(" ", "")) > Integer.parseInt(endDate.getDay().replace(" ", ""))) {
						flag = false;
					}
				}
				else flag = true;
			}
			else flag = true;
			return flag;
		}
		
		public void systemRec() {
	        if(Day % 1 == 0) {
	            System.out.println("\n\n     --- System Recommendations ----");
	            

	            for (int i = 0; i < counter; i++) {
	            	int isCarRequested = 0;
		            int isEmployeeAvaliable = 0;

	                if(offices[i].getEmployeeCounter() == 0) {
	                    System.out.println("     Office"+ offices[i].getID() + ": There is no employee. Office can be deleted.");
	                }

	                for (int j = 0; j < offices[i].getCarCounter(); j++) {
	                    if(offices[i].getCars()[j].isRequested()) isCarRequested++;
	                }
	                if(offices[i].getCarCounter()-1 == isCarRequested || offices[i].getCarCounter() == isCarRequested) {
	                    System.out.println("     Office"+ offices[i].getID() + ": There is not enough cars for demands. More cars can be added.");
	                }


	                for (int j = 0; j < offices[i].getEmployeeCounter(); j++) {
	                    if(offices[i].getEmployees()[j].isContract()) isEmployeeAvaliable++;
	                }
	                if(offices[i].getEmployeeCounter() == isEmployeeAvaliable) {
	                    System.out.println("     Office"+ offices[i].getID() + ": There is not enough employees for demands. More employees can be hired.");
	                }


	                if(offices[i].getEmployeeCounter() == 3)
	                    System.out.println("     Office"+ offices[i].getID() + ": There are too many employees for an office. A new office can be opened.");
	            }
	        }
	    }
		
		//NEXTDAY    
		public void nextDay() {	
			int[] rentedCars = new int[50];
			
			//SETTING CAR KM
			for (int i = 0; i < contractCounter; i++) {
		    	Random rnd = new Random();
		    	int random = rnd.nextInt(3)+1;
		    	if(contracts[i] != null && offices[contracts[i].getOfficeID()-1] != null && offices[contracts[i].getOfficeID()-1].getCars()[contracts[i].getCarID()-1] != null) {
			    	if(random == 1) {
			    		offices[contracts[i].getOfficeID()-1].getCars()[contracts[i].getCarID()-1].setKilometers(Integer.parseInt(offices[contracts[i].getOfficeID()-1].getCars()[contracts[i].getCarID()-1].getKilometers()) + 100);
			    	}
			    	else if (random == 2) {
			    		offices[contracts[i].getOfficeID()-1].getCars()[contracts[i].getCarID()-1].setKilometers(Integer.parseInt(offices[contracts[i].getOfficeID()-1].getCars()[contracts[i].getCarID()-1].getKilometers()) + 200);
			    	}
			    	else
			    		offices[contracts[i].getOfficeID()-1].getCars()[contracts[i].getCarID()-1].setKilometers(Integer.parseInt(offices[contracts[i].getOfficeID()-1].getCars()[contracts[i].getCarID()-1].getKilometers()) + 300);
		    	}
		    }
			
			System.out.println("---- OFFICE PROFITS ----");
			
			for (int i1 = 0; i1 < counter; i1++) {
				int incomes = 0;
				int expenses = 0;
				int employeeSalaries = 0;
				int performanceBonus = 0;
				int[] maintenanceExpenses = new int[50];
				int[] CarInfo = new int[50];
				int[] bonusInfo = new int[5];
				int bonusInfoCounter = 0;
				
				if(offices[i1] != null) {
					System.out.print("Office" + offices[i1].getID() + " incomes: ");
					if(offices[i1].getSignContract() == 0){
						System.out.println(incomes + " cp");
						expenses += 100;
						//EMPLOYEE SALARIES
						for (int j = 0; j < offices[i1].getEmployeeCounter(); j++) {
							if(offices[i1].getEmployees()[j] != null) {
								employeeSalaries = 30;
								expenses += employeeSalaries;
							}
						}
						System.out.println("Office" + offices[i1].getID() + " expenses: " + expenses + " cp");
						System.out.println("\t Office Rent: 100");
						System.out.println("\t Employee Salaries: " + employeeSalaries);
					}
					else {
						for (int i = 0; i < contractCounter; i++) {
							if(contracts[i] != null && offices[contracts[i].getOfficeID()-1] != null 
									&& offices[contracts[i].getOfficeID()-1].getCars()[contracts[i].getCarID()-1] != null) {
								if(contracts[i].getOfficeID()-1 == i1) {
									//INCOME
									for (int j = 0; j < contractCounter; j++) {
										if(contracts[j] != null && contracts[j].getOfficeID()-1 == i1) {
											incomes += contracts[j].rentalIncome(offices[contracts[j].getOfficeID()-1].getCars()[contracts[j].getCarID()-1].getClass1());
											CarInfo[j*2] = contracts[j].getCarID();
											CarInfo[(j*2)+1] = Integer.parseInt(offices[contracts[j].getOfficeID()-1].getCars()[contracts[j].getCarID()-1].getKilometers());
										}
									}						
									//OFFICE RENT
									expenses += 100;
									//EMPLOYEE SALARIES
									for (int j = 0; j < offices[i1].getEmployeeCounter(); j++) {
										if(offices[i1].getEmployees()[j] != null) {
											employeeSalaries += 30;
											expenses += 30;
											//PERFORMANCE BONUS
											if(offices[i1].getEmployees()[j].isContract()) {
												performanceBonus = contracts[j].performanceBonus(offices[contracts[j].getOfficeID()-1].getCars()[contracts[j].getCarID()-1].getClass1());
												bonusInfo[j] = performanceBonus;
												bonusInfoCounter++;
												expenses += performanceBonus;
											}
										}
									}									
									//MAINTENANCE EXPENSES
									for (int j = 0; j < contractCounter; j++) {
										if(contracts[j] != null && contracts[j].getOfficeID()-1 == i1) {
											maintenanceExpenses[j] = contracts[j].maintenance(offices[contracts[j].getOfficeID()-1].getCars()[contracts[j].getCarID()-1].getClass1(), Integer.parseInt(offices[contracts[j].getOfficeID()-1].getCars()[contracts[j].getCarID()-1].getKilometers()));
											expenses += maintenanceExpenses[j];
										}
									}
									break;
								}
							}
						}
						System.out.println(incomes + " cp");
						for (int i = 0; i < contractCounter; i++) {
							if(contracts[i] != null && offices[contracts[i].getOfficeID()-1] != null 
									&& offices[contracts[i].getOfficeID()-1].getCars()[contracts[i].getCarID()-1] != null) {
								if(contracts[i].getOfficeID()-1 == i1) {
									for (int j = 0; j < contractCounter; j++) {
										if(contracts[j] != null && contracts[j].getOfficeID()-1 == i1)
											System.out.println("\t Car" + CarInfo[j*2] + ": " + contracts[j].rentalIncome(offices[contracts[j].getOfficeID()-1].getCars()[contracts[j].getCarID()-1].getClass1()));	
									}
									
									System.out.println("Office" + offices[i1].getID() + " expenses: " + expenses + " cp");
									System.out.println("\t Office Rent: 100");
									System.out.println("\t Employee Salaries: " + employeeSalaries);
									System.out.print("\t Employee performance bonuses: ");
									performanceBonus = 0;
									for (int j = 0; j < bonusInfoCounter; j++) {
										System.out.print(bonusInfo[j]);
										performanceBonus += bonusInfo[j];
										if(bonusInfo[j+1] != 0)
											System.out.print(" + ");
										else break;
									}
									System.out.println(" = " + performanceBonus);
									for (int j = 0; j < contractCounter; j++) {
										if(contracts[j] != null && contracts[j].getOfficeID()-1 == i1) {
											System.out.print("\t Car" + CarInfo[j*2] + " maintenance: ");
										
											if(contracts[j] != null && offices[contracts[j].getOfficeID()-1].getCars()[contracts[j].getCarID()-1].getClass1().equalsIgnoreCase("economy"))
												System.out.println(20 + " + " + ((Integer.parseInt(offices[contracts[j].getOfficeID()-1].getCars()[contracts[j].getCarID()-1].getKilometers())/100)*5) + " = " + maintenanceExpenses[j] + " (" + offices[contracts[j].getOfficeID()-1].getCars()[contracts[j].getCarID()-1].getKilometers() + ")");
											else if(offices[contracts[j].getOfficeID()-1].getCars()[contracts[j].getCarID()-1].getClass1().equalsIgnoreCase("sports"))
												System.out.println(70 + " + " + ((Integer.parseInt(offices[contracts[j].getOfficeID()-1].getCars()[contracts[j].getCarID()-1].getKilometers())/100)*10) + " = " + maintenanceExpenses[j] + " (" + offices[contracts[j].getOfficeID()-1].getCars()[contracts[j].getCarID()-1].getKilometers() + ")");
											else
												System.out.println(120 + " + " + ((Integer.parseInt(offices[contracts[j].getOfficeID()-1].getCars()[contracts[j].getCarID()-1].getKilometers())/100)*15) + " = " + maintenanceExpenses[j] + " (" + offices[contracts[j].getOfficeID()-1].getCars()[contracts[j].getCarID()-1].getKilometers() + ")");
										}
									}
								break;
								}
							}
						}
					}
					offices[i1].setMoney(offices[i1].getMoney() + incomes - expenses);
					System.out.println("Office " + offices[i1].getID() + " profit: " + (incomes - expenses) + "\n");
				}
			}
			
			for (int j = 0; j < counter; j++) {
				if(offices[j] != null) {
					int carID=0;
					int carID1=0;
					int bigger = 0;
					int bigger1 = 0;
					int bigger2 = 0;
					int bigger3 = 0;
					int customerID1 = 0;
					int employeeID1 = 0;
					double differance = 0;
					int startDay= 0;
					int endDay = 0;
					int[] CustomerIds = new int[contractCounter];
					int[] EmployeeIds = new int[contractCounter];
					int[] allEmployes = new int[10];
					int[] allCustomers = new int[10];
					int[] highestProfit = new int[50];
					int biggestProfit = 0;
					int highestProfitID = 0;
					int z=0;
					
					for (int i = 0; i < contractCounter; i++) {
						if(contracts[i] != null && contracts[i].getOfficeID()-1 == j) {
							highestProfit[i] = Integer.parseInt(offices[j].getCars()[contracts[i].getCarID()-1].getKilometers()) * contracts[i].rentalIncome(offices[j].getCars()[contracts[i].getCarID()-1].getClass1()) * ((Integer.parseInt(contracts[i].getEndDate().getDay())- Integer.parseInt(contracts[i].getStartDate().getDay())+1));
						}
					}
					for (int i = 0; i < contractCounter; i++) {
						if(offices[j].getCars()[contracts[i].getCarID()-1].isRequested())
							if(biggestProfit < highestProfit[i]) {
								biggestProfit = highestProfit[i];
								highestProfitID = contracts[i].getCarID()-1;
								z = i;
							}
					}
					
					for (int k = 0; k < offices[j].getCarCounter(); k++) {
						if(offices[j].getCars()[k].isRequested()) {
							rentedCars[k]++;
						}
					}
					for (int k = 0; k < offices[j].getCarCounter(); k++) {
						if(offices[j].getCars()[k].isRequested()) {
							if(bigger < rentedCars[k]) {
								bigger = rentedCars[k];
								carID=k;
							}
							else if(bigger == rentedCars[k]) {
								bigger1 = rentedCars[k];
								carID1 = k;
							}
						}
					}
					for (int i = 0; i < contractCounter; i++) {
						if(contracts[i] != null && offices[j].getCars()[contracts[i].getCarID()-1].isRequested) {
							CustomerIds[i] = contracts[i].getCustomerID();
							EmployeeIds[i] = contracts[i].getEmployeeID();
							startDay = Integer.parseInt(contracts[i].getStartDate().getDay());
							endDay = Integer.parseInt(contracts[i].getEndDate().getDay());
							differance += endDay - startDay + 1;
							}
						}
					for (int i = 0; i < contractCounter; i++) {
						for (int i2 = 0; i2 < customer_counter; i2++) {
							if(contracts[i] != null && contracts[i].getOfficeID() == j) {
								for (int k = 0; k < CustomerIds.length; k++) {
									if(customers[i2].getID() == CustomerIds[k] && Day == Integer.parseInt(contracts[i].getStartDate().getDay())) {
											allCustomers[i2]++;
									}										
								}	
							}
						}
					}
					for (int i = 0; i < offices[j].getEmployeeCounter(); i++) {
						for (int k = 0; k < EmployeeIds.length; k++) {
							if (EmployeeIds[k] == offices[j].getEmployees()[i].getID()) {
								if(Day == Integer.parseInt(contracts[i].getStartDate().getDay()))
									allEmployes[i]++;							
							}
						}
					}
					for (int k = 0; k < customer_counter; k++) {
						if(bigger2 < allCustomers[k]) {
							bigger2 = allCustomers[k];
							customerID1=k;
							}
						}							
						for (int k = 0; k < offices[j].getEmployeeCounter(); k++) {
							if(bigger3 < allEmployes[k]) {
								 bigger3 = allEmployes[k];
								employeeID1=k;
							}
						}
						boolean flag=false;
						for (int i = 0; i <offices[j].getCarCounter() ; i++) {
							if(offices[j].getCars()[i].isRequested) {
								flag = true;
								break;
							}
						}
						if(flag == true)
						{
							System.out.println("-------Office " + offices[j].getID() + " Statics for last 10 days-------\n");
							if(bigger1 == bigger) {
								System.out.println("Most rented car: Car" + (carID+1) + ";" + offices[j].getCars()[carID].getBrand() + ";" + offices[j].getCars()[carID].getModel() + "    Car"+ (carID1+1) + ";" + offices[j].getCars()[carID1].getBrand() + ";" + offices[j].getCars()[carID1].getModel());
								System.out.println("Most rented car class:" + offices[j].getCars()[carID].getClass1() + " - " + offices[j].getCars()[carID1].getClass1());
							}
							else {
								System.out.println("Most rented car: Car" + (carID+1) + ";" + offices[j].getCars()[carID].getBrand() + ";" + offices[j].getCars()[carID].getModel());
								System.out.println("Most rented car class;" + offices[j].getCars()[carID].getClass1());
							}
							System.out.println("The car with the highest profit: Car" + (highestProfitID+1) + ";" + offices[j].getCars()[highestProfitID].getBrand() + ";" + offices[j].getCars()[highestProfitID].getModel());
							System.out.println("The car class with the highest profit: " + offices[j].getCars()[highestProfitID].getClass1());
							System.out.println("The average number of days the cars are rented: " + (double) differance/contractCounter + " days");
							System.out.println("The customer who rented most: Customer" + (customerID1+1)+ ";" + customers[customerID1].getName()+ ";" + customers[customerID1].getSurname());						
							System.out.println("The employee who rented most: Employee" + (employeeID1+1) + ";" + offices[j].getEmployees()[employeeID1].getName()+ ";" + offices[j].getEmployees()[employeeID1].getSurname());
							System.out.println("The most profitable employee: Employee" + offices[contracts[z].getOfficeID()-1].getEmployees()[contracts[z].getEmployeeID()].getID() + ";" + offices[contracts[z].getOfficeID()-1].getEmployees()[contracts[z].getEmployeeID()].getName() + ";" + offices[contracts[z].getOfficeID()-1].getEmployees()[contracts[z].getEmployeeID()].getSurname() + "(" + contracts[z].rentalIncome(offices[contracts[z].getOfficeID()-1].getCars()[contracts[z].getCarID()-1].getClass1()) + " - " + contracts[z].maintenance(offices[contracts[z].getOfficeID()-1].getCars()[contracts[z].getCarID()-1].getClass1(), Integer.parseInt(offices[contracts[z].getOfficeID()-1].getCars()[contracts[z].getCarID()-1].getKilometers())) + " = " + (contracts[z].rentalIncome(offices[contracts[z].getOfficeID()-1].getCars()[contracts[z].getCarID()-1].getClass1()) - contracts[z].maintenance(offices[contracts[z].getOfficeID()-1].getCars()[contracts[z].getCarID()-1].getClass1(), Integer.parseInt(offices[contracts[z].getOfficeID()-1].getCars()[contracts[z].getCarID()-1].getKilometers()))) + ")");
							double sum = 0;
							double sum1 = 0;
							int count = 0;
							System.out.print("Average income levels of the employees for the office: (");
							for (int k = 0; k < contractCounter; k++) {
								if(contracts[k] != null && contracts[k].getOfficeID()-1 == j) {
									if(count != 0)
										System.out.print(" + ");
									count++;
									sum += contracts[k].rentalIncome(offices[contracts[k].getOfficeID()-1].getCars()[contracts[k].getCarID()-1].getClass1()) - contracts[k].maintenance(offices[contracts[k].getOfficeID()-1].getCars()[contracts[k].getCarID()-1].getClass1(), Integer.parseInt(offices[contracts[k].getOfficeID()-1].getCars()[contracts[k].getCarID()-1].getKilometers()));
									sum1 = contracts[k].rentalIncome(offices[contracts[k].getOfficeID()-1].getCars()[contracts[k].getCarID()-1].getClass1()) - contracts[k].maintenance(offices[contracts[k].getOfficeID()-1].getCars()[contracts[k].getCarID()-1].getClass1(), Integer.parseInt(offices[contracts[k].getOfficeID()-1].getCars()[contracts[k].getCarID()-1].getKilometers()));
									System.out.print(sum1);
								}
							}
							System.out.println(")/" + offices[j].getEmployeeCounter() + " = " + sum/offices[j].getEmployeeCounter());
							System.out.println();
						}
					}
				}
			
			Day++;
			
			if(Day > 30) {
		        Month++;
		        Day-=30;
		    }
	        if(Month > 12) {
	            Year++;
	            Month-=12;
	        }
	        systemRec();

	        for (int i = 0; i < contractCounter; i++) {
				if(contracts[i] != null && offices[contracts[i].getOfficeID()-1] != null && offices[contracts[i].getOfficeID()-1].getCars()[contracts[i].getCarID()-1] != null) {
					offices[contracts[i].getOfficeID()-1].getEmployees()[contracts[i].getEmployeeID()].setContract(false);
					if(contracts[i].getEndDate().getDay().equalsIgnoreCase(String.valueOf(Day))) {
						offices[contracts[i].getOfficeID()-1].getCars()[contracts[i].getCarID()-1].setRequested(false);
						offices[contracts[i].getOfficeID()-1].setSignContract(offices[contracts[i].getOfficeID()-1].getSignContract()-1);
						contracts[i] = null;
					}
				}
			}
			System.out.println("--- Date: " + Day + "." + Month + "." + Year + " ---");
		}
}
