
public class Office {
	private int ID;
	private String PhoneNumber;
	private String Address;
	private String City;
	private Employee[] employees;
	private int employeeCounter;
	private int carCounter;
	private Car[] cars;
	private int signContract;
	private int money;
	
	
	public Car[] getCars() {
		return cars;
	}
	public void setEmployeeCounter(int employeeCounter) {
		this.employeeCounter = employeeCounter;
	}
	public int getCarCounter() {
		return carCounter;
	}

	public void setCarCounter(int carCounter) {
		this.carCounter = carCounter;
	}
	//Constructor
		public Office(String phoneNumber, String address, String city) {
			PhoneNumber = phoneNumber;
			Address = address;
			City = city;
			employees = new Employee[3];
			employeeCounter = 0;
			carCounter = 0;
			cars = new Car[30];
			signContract = 0;
			money = 0;
		}
	//Getters
		public int getID() {
			return ID;
		}
		
		public String getPhoneNumber() {
			return PhoneNumber;
		}
		public String getAddress() {
			return Address;
		}
		public String getCity() {
			return City;
		}
		public Employee[] getEmployees() {
			return employees;
		}
		public int getEmployeeCounter() {
			return employeeCounter;
		}
			
		public int getSignContract() {
			return signContract;
		}
		public int getMoney() {
			return money;
		}
		//Setters
		public void setID(int iD) {
			ID = iD;
		}
		
		public void setSignContract(int signContract) {
			this.signContract = signContract;
		}
		
		public void setMoney(int money) {
			this.money = money;
		}
		//METHODS
		// Adding Car
		public void addCar(Car car) {
			if (carCounter == 30) {
				 System.out.print("There is no empty space for new car");
			}
			else {
				cars[carCounter] = car;
				cars[carCounter].setiD(carCounter+1);
				carCounter++;			
			}
		}

		public void listCar() {
			for (int i = 0; i < carCounter; i++) {
				if(cars[i] != null) {
					String isRequested = "Avaliable";
					if(cars[i].isRequested) isRequested = "Non - Avaliable";
					System.out.println("  "+ cars[i].getiD() +". "+ cars[i].getBrand() + ";" + cars[i].getModel()+ ";"+cars[i].getClass1()+";" + cars[i].getKilometers() + ";" + cars[i].getOfficeID() + " - " + isRequested);
				}
				else {
					System.out.println("There is no car in this office");
				}
					
			}
		}
		
		
		//Adding Employee
		public void addEmployee(Employee employee, int officeID) {
			if(employeeCounter < 3) {
				employees[employeeCounter] = employee;
				employees[employeeCounter].setID(employeeCounter+1);
				employees[employeeCounter].setOfficeID(officeID);
				employeeCounter++;
			}
			else
				System.out.println("There is no empty place for a new Employee!");
		}
		
		//Listing Employees
		public void listEmployee() {
			int counter = 0;
			for (int j = 0; j < employeeCounter; j++) {
				if(employees[j] != null) {
					System.out.println("  " + employees[j].getID() +".Employee;"+employees[j].getName()+";"+employees[j].getSurname()+";"+employees[j].getGender()+";"+employees[j].getBirthdate()+";"+employees[j].getOfficeID());
					counter++;
				}
			}
			if(counter == 0)
				System.out.println("There is no employee in this office");
		}
		
		//Delete Employee
		public void deleteEmployee(int employeeID) {
			if(employees[employeeID] == null)
				System.out.println("Employee " + (employeeID+1) + " does not exist");
			else {
				employees[employeeID] = null;
			}
		}	
}
