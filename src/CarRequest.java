
public class CarRequest {
	private int ID;
	private int OfficeID; 
	private int CustomerID;
	private String Brand;
	private String Model;
	private String Class;
	private Date StartDate;
	private Date EndDate;
	private String contract;

	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}

	public CarRequest(int officeID, int customerID, String brand, String model, String class1,
			Date startDate, Date endDate) {
		OfficeID = officeID;
		CustomerID = customerID;
		Brand = brand;
		Model = model;
		Class = class1;
		StartDate = startDate;
		EndDate = endDate;
	}
	
	public void listCarRequest() {
		
		System.out.println("   " + contract + "/ "+ (ID+1) + ".CarRequest;" + OfficeID + ";" + CustomerID + ";" + Brand + ";" + Model + ";" + Class + ";" + StartDate.listDate() + ";" + EndDate.listDate());
		
	}
	
	


	public int getID() {
		return ID;
	}
	public int getOfficeID() {
		return OfficeID;
	}
	public int getCustomerID() {
		return CustomerID;
	}
	public String getBrand() {
		return Brand;
	}
	public String getModel() {
		return Model;
	}
	public String getClass1() {
		return Class;
	}
	public Date getStartDate() {
		return StartDate;
	}
	public Date getEndDate() {
		return EndDate;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public void setOfficeID(int officeID) {
		OfficeID = officeID;
	}
	public void setCustomerID(int customerID) {
		CustomerID = customerID;
	}
	public void setBrand(String brand) {
		Brand = brand;
	}
	public void setModel(String model) {
		Model = model;
	}
	public void setClass(String class1) {
		Class = class1;
	}
	public void setStartDate(Date startDate) {
		StartDate = startDate;
	}
	public void setEndDate(Date endDate) {
		EndDate = endDate;
	}
    
}
