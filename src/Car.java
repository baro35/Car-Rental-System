
public class Car {

	private int iD;
    private String brand;
    private String model;
    private String classs;
    private String kilometers;
    private int officeID;
    boolean isRequested = false;

    
	public Car(String Brand, String Model, String Classs, String Kilometers, int OfficeID) {
		brand = Brand;
		model = Model;
		classs = Classs;
		kilometers = Kilometers;
		officeID = OfficeID;
    }
	
	public boolean isRequested() {
		return isRequested;
	}
	public void setRequested(boolean isRequested) {
		this.isRequested = isRequested;
	}
	public int getiD() {
		return iD;
	}
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String Model) {
        model = Model;
    }
    public String getClass1() {
        return classs;   
    }
    public void seyClass(String Classs) {
        classs = Classs;
    }
    public String getKilometers() {
        return kilometers;   
    }
    public void setKilometers(int Kilometers) {
        kilometers = String.valueOf(Kilometers);
    }
    public int getOfficeID() {
    	return officeID;
    }
    public void setOfficeID(int OfficeID) {
    	officeID = OfficeID;
    }
	public void setiD(int iD) {
		this.iD = iD;
	}
    
}
