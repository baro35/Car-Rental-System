
public class Contract {
	private int ID;
	private int EmployeeID;
	private int CustomerID;
	private int CarID;
	private Date StartDate;
	private Date EndDate;
	private int OfficeID;
	
	public Contract(int iD, int employeeID, int customerID, int carID, Date startDate, Date endDate) {
		ID = iD;
		EmployeeID = employeeID;
		CustomerID = customerID;
		CarID = carID;
		StartDate = startDate;
		EndDate = endDate;
	}
	
	
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getEmployeeID() {
		return EmployeeID;
	}
	public void setEmployeeID(int employeeID) {
		EmployeeID = employeeID;
	}
	public int getCustomerID() {
		return CustomerID;
	}
	public void setCustomerID(int customerID) {
		CustomerID = customerID;
	}
	public int getCarID() {
		return CarID;
	}
	public void setCarID(int carID) {
		CarID = carID;
	}
	public Date getStartDate() {
		return StartDate;
	}
	public void setStartDate(Date startDate) {
		StartDate = startDate;
	}
	public Date getEndDate() {
		return EndDate;
	}
	public void setEndDate(Date endDate) {
		EndDate = endDate;
	}
	public int getOfficeID() {
		return OfficeID;
	}
	public void setOfficeID(int officeID) {
		OfficeID = officeID;
	}



	public void listContract() {

        System.out.println("  " + (ID+1) + ".Contract;Employee" + (EmployeeID+1) + ";Customer" + CustomerID + ";Car" + CarID + ";" + StartDate.listDate() + ";" + EndDate.listDate());

    }
	
	public int maintenance(String classs, int kilometers) {
    	int maintenance;
    	if(classs.equalsIgnoreCase("economy"))
    		maintenance = 20 + ((kilometers/100)*5);
    	else if(classs.equalsIgnoreCase("sports"))
    		maintenance = 70 + ((kilometers/100)*10);
    	else
    		maintenance = 120 + ((kilometers/100)*15);
    	return maintenance;
    }
    
    public int rentalIncome(String classs) {
    	if(classs.equalsIgnoreCase("economy")) {
    		return 100;
    	}
    	else if(classs.equalsIgnoreCase("sports")) {
    		return 200;
    	}
    	else {
    		return 300;
    	}
    }
	
    public int performanceBonus(String classs) {
    	if(classs.equalsIgnoreCase("economy")) {
    		return 5;
    	}
    	else if(classs.equalsIgnoreCase("sports")) {
    		return 10;
    	}
    	else {
    		return 15;
    	}
    }
}
