

public class Employee {
	private int ID;
	private String Name;
	private String Surname;
	private String Gender;
	private String Birthdate;
	private int OfficeID;
	private boolean isContract = false;
	


	
	//Constructor
	public Employee(String name, String surname, String gender, String birthdate, int officeID) {
		Name = name;
		Surname = surname;
		Gender = gender;
		Birthdate = birthdate;
		OfficeID = officeID;
	}
	
	//Getters
	public int getID() {
		return ID;
	}
	public String getName() {
		return Name;
	}
	public String getSurname() {
		return Surname;
	}
	public String getGender() {
		return Gender;
	}
	public String getBirthdate() {
		return Birthdate;
	}
	public int getOfficeID() {
		return OfficeID;
	}
	public boolean isContract() {
		return isContract;
	}

	
	//Setters
	public void setID(int iD) {
		ID = iD;
	}
	public void setOfficeID(int officeID) {
		OfficeID = officeID;
	}
	public void setContract(boolean isContract) {
		this.isContract = isContract;
	}
}
